package com.ubo.terminal.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.common.terminal.UboTerminalMessage;
import com.ubo.terminal.SessionChannelHandler;

import dudu.service.core.utils.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MixFrameDecoder extends MessageToMessageDecoder<ByteBuf> {

    private static final Logger logger =
            LoggerFactory.getLogger(MixFrameDecoder.class);

    private int state;
    private int length;
    private int dataLength;
    private int frameLength;
    private UboTerminalMessage tMsg;
    private boolean paragraphOver;//该段落是否解析完成
    private int paragraphTotalLen;//该字段的总长度
    private int paragraphReadedLen;//已经读取成功的字节数
    private byte[] paragraph;//一个段落的内容

    public final static int STATE_IDLE = 0;//开始[
    public final static int STATE_ID = 1;//唯一标示
    public final static int STATE_VERSION = 2;//版本
    public final static int STATE_TYPE = 3;//设备类型
    public final static int STATE_SERIAL = 4;//流水
    public final static int STATE_COMMAND = 5;//报文类型
    public final static int STATE_TEXT = 7;//报文体
    public final static int STATE_TIME = 8;//上报时间
    public final static int STATE_TAIL = 9;//结束符]

    private static long beginTime = 0;

    public MixFrameDecoder() {
        state = STATE_IDLE;
        paragraphOver = true;
        paragraphReadedLen = 0;
        paragraphTotalLen = 0;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        SessionChannelHandler sessionHandler = (SessionChannelHandler) ctx.channel().pipeline().get(SessionChannelHandler.class.getName());
        String sessionToken = sessionHandler.getSession().getToken();

        logger.debug("decode()");
        logger.debug(String.format("[%s]buffer.readableBytes: \'0x%x\' --> %d", sessionToken, msg.readableBytes(), msg.readableBytes()));

        ByteBuf buffer = msg;


        if (logger.isDebugEnabled()) {
            Utils.dumpBuffer2(buffer);
        }
		/*不能根据包大小来丢包，可能会出现TCP粘包拆包现象导致半包数据
		//Make sure if the command field was received. (MEDIA cross package  So read continue: add to if(..&& state != STATE_MEDIA) ) 
	    if (buffer.readableBytes() < 40 ) {
	        // The command field was not received yet - return null.
	        // This method will be invoked again when more packets are
	        // received and appended to the buffer.
	        return;
	    }
	    */

        /**
         * 51 message
         * [imei or terminal_id,serial_no,type,sub_type,zip_flag,total_package,package_no,length,data]
         *
         * other message
         * [imei or terminal_id(15),serial_no(18),type(2),data(1+)]
         *
         * length: field length
         * frameLength: raw message frame length
         * dataLength:
         *
         * frameLength >= 40
         *
         * */

        logger.debug("[" + sessionToken + "]decoder state: " + state);

        byte byteCode;
        while (buffer.isReadable()) {

            switch (state) {
                case STATE_IDLE:
                    byteCode = buffer.readByte();
                    logger.info("msg的readByte:{}", byteCode);
                    //pick up the frame header.
                    if (byteCode == 0x5B) { /*'['*/
                        int readerIndex = buffer.readerIndex();
                        //logger.debug("readerIndex: {}.", readerIndex);
                        buffer.markReaderIndex(); /*mark next field start point*/
                        beginTime = System.currentTimeMillis();
                        length = 0;
                        frameLength = 1;

                        tMsg = new UboTerminalMessage();

                        state = STATE_ID;

                        logger.debug("[{}]decoded frame header: state=STATE_ID,readerIndex={},frameLength={}.", sessionToken, readerIndex, frameLength);
                    } else {
                        //skip unknown data
                        logger.warn(String.format("[%s]STATE_IDLE::Unknow data: \'0x%x\'", sessionToken, byteCode));
                    }
                    break;

                case STATE_ID:
                    /**1*******************/
                    paragraphTotalLen = 18;
                    /*********************/
                    byteCode = buffer.readByte();
                    length++;
                    //parse terminal id
                    if (byteCode == 0x2C) { /* ',' */
                        buffer.resetReaderIndex();
                        ByteBuf frame = buffer.readBytes(length - 1);


                        if (logger.isDebugEnabled()) {
                            Utils.dumpBuffer(frame);
                        }


                        buffer.skipBytes(1); /*skip field separator ','*/
                        buffer.markReaderIndex(); /*mark the next field start point*/

                        frameLength += length;
                        length = 0;
                        /**3.1****************************/
                        byte[] textBytes;
                        if (paragraphOver) {
                            textBytes = new byte[frame.readableBytes()];
                            frame.readBytes(textBytes);
                        } else {
                            frame.readBytes(paragraph, paragraphReadedLen, frame.readableBytes());
                            textBytes = paragraph;
                        }
                        paragraphOver = true;
                        paragraphReadedLen = 0;
                        /*****************************/
                        String text = new String(textBytes, "UTF-8");
                        logger.debug("[{}]decoded 唯一标识: \"{}\".", sessionToken, text);
                        tMsg.setTid(text);

                        state = STATE_VERSION;

                        int readerIndex = buffer.readerIndex();
                        logger.debug("[{}]decode terminal id: state=STATE_SERIAL,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                    } else if (length > 20) { //length of imei or terminal id is less than 18.
                        /**3.2************************/
                        paragraphOver = true;
                        length = 0;
                        paragraphReadedLen = 0;
                        /**************************/
                        buffer.resetReaderIndex();
                        int readerIndex = buffer.readerIndex();
                        /*total length from '[' ('[' is included)*/
                        //frameLength += length;
                        //回滚到STATE_ID解析前的readerIndex和frameLength
                        logger.debug("[{}]decode fail: state=STATE_ID,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                        /*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
                        buffer.readerIndex(readerIndex - frameLength + 1);

                        state = STATE_IDLE;
                    }
                    /**2************************/
                    //处理半包数据
                    decodeHalfTcpPkg(buffer);
                    /**************************/
                    break;


                case STATE_VERSION:
                    /**1*******************/
                    paragraphTotalLen = 2;
                    /*********************/
                    byteCode = buffer.readByte();
                    length++;
                    //parse terminal id
                    if (byteCode == 0x2C) { /* ',' */
                        buffer.resetReaderIndex();
                        ByteBuf frame = buffer.readBytes(length - 1);


                        if (logger.isDebugEnabled()) {
                            Utils.dumpBuffer(frame);
                        }


                        buffer.skipBytes(1); /*skip field separator ','*/
                        buffer.markReaderIndex(); /*mark the next field start point*/

                        frameLength += length;
                        length = 0;
                        /**3.1****************************/
                        byte[] textBytes;
                        if (paragraphOver) {
                            textBytes = new byte[frame.readableBytes()];
                            frame.readBytes(textBytes);
                        } else {
                            frame.readBytes(paragraph, paragraphReadedLen, frame.readableBytes());
                            textBytes = paragraph;
                        }
                        paragraphOver = true;
                        paragraphReadedLen = 0;
                        /*****************************/
                        String text = new String(textBytes, "UTF-8");
                        logger.debug("[{}]decoded 格式版本 : \"{}\".", sessionToken, text);
                        tMsg.setDeviceVersion(text);

                        state = STATE_TYPE;

                        int readerIndex = buffer.readerIndex();
                        logger.debug("[{}]decode terminal id: state=STATE_SERIAL,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                    } else if (length > 2) { //length of imei or terminal id is less than 18.
                        /**3.2************************/
                        paragraphOver = true;
                        length = 0;
                        paragraphReadedLen = 0;
                        /**************************/
                        buffer.resetReaderIndex();
                        int readerIndex = buffer.readerIndex();
                        /*total length from '[' ('[' is included)*/
                        //frameLength += length;
                        //回滚到STATE_ID解析前的readerIndex和frameLength
                        logger.debug("[{}]decode fail: state=STATE_ID,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                        /*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
                        buffer.readerIndex(readerIndex - frameLength + 1);

                        state = STATE_IDLE;
                    }
                    /**2************************/
                    //处理半包数据
                    decodeHalfTcpPkg(buffer);
                    /**************************/
                    break;

                case STATE_TYPE:
                    /**1*******************/
                    paragraphTotalLen = 1;
                    /*********************/
                    byteCode = buffer.readByte();
                    length++;
                    //parse terminal id
                    if (byteCode == 0x2C) { /* ',' */
                        buffer.resetReaderIndex();
                        ByteBuf frame = buffer.readBytes(length - 1);


                        if (logger.isDebugEnabled()) {
                            Utils.dumpBuffer(frame);
                        }


                        buffer.skipBytes(1); /*skip field separator ','*/
                        buffer.markReaderIndex(); /*mark the next field start point*/

                        frameLength += length;
                        length = 0;
                        /**3.1****************************/
                        byte[] textBytes;
                        if (paragraphOver) {
                            textBytes = new byte[frame.readableBytes()];
                            frame.readBytes(textBytes);
                        } else {
                            frame.readBytes(paragraph, paragraphReadedLen, frame.readableBytes());
                            textBytes = paragraph;
                        }
                        paragraphOver = true;
                        paragraphReadedLen = 0;
                        /*****************************/
                        String text = new String(textBytes, "UTF-8");
                        logger.debug("[{}]decoded terminal id: \"{}\".", sessionToken, text);
                        tMsg.setDeviceType(text);

                        state = STATE_SERIAL;

                        int readerIndex = buffer.readerIndex();
                        logger.debug("[{}]decode 设备类型: state=STATE_SERIAL,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                    } else if (length > 1) { //length of imei or terminal id is less than 18.
                        /**3.2************************/
                        paragraphOver = true;
                        length = 0;
                        paragraphReadedLen = 0;
                        /**************************/
                        buffer.resetReaderIndex();
                        int readerIndex = buffer.readerIndex();
                        /*total length from '[' ('[' is included)*/
                        //frameLength += length;
                        //回滚到STATE_ID解析前的readerIndex和frameLength
                        logger.debug("[{}]decode fail: state=STATE_ID,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                        /*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
                        buffer.readerIndex(readerIndex - frameLength + 1);

                        state = STATE_IDLE;
                    }
                    /**2************************/
                    //处理半包数据
                    decodeHalfTcpPkg(buffer);
                    /**************************/
                    break;


                 case STATE_SERIAL:
                    //**1*******************//*
                    paragraphTotalLen = 18;
                    //*********************//*
                    byteCode = buffer.readByte();

                    length++;

                    //parse serial NO.
                    if (byteCode == 0x2C) { //* ',' *//*
                        buffer.resetReaderIndex();
                        ByteBuf frame = buffer.readBytes(length - 1);

                        if (logger.isDebugEnabled()) {
                            Utils.dumpBuffer(frame);
                        }

                        buffer.skipBytes(1); //*skip field separator ','*//*
                        buffer.markReaderIndex(); //*mark the next field start point*//*

                        frameLength += length;
                        length = 0;

                        //**3.1****************************//*
                        byte[] textBytes;
                        if (paragraphOver) {
                            textBytes = new byte[frame.readableBytes()];
                            frame.readBytes(textBytes);
                        } else {
                            frame.readBytes(paragraph, paragraphReadedLen, frame.readableBytes());
                            textBytes = paragraph;
                        }
                        paragraphOver = true;
                        paragraphReadedLen = 0;
                        //*****************************//*
                        String text = new String(textBytes, "UTF-8");
                        logger.debug("[{}]decoded 交易流水号: \"{}\".", sessionToken, text);
                        tMsg.setSerial(text);

                        state = STATE_COMMAND;

                        int readerIndex = buffer.readerIndex();
                        logger.debug("[{}]decode serial NO: state=STATE_COMMAND,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                    } else if (length > 18) { //length of serial NO is 18.
                        //**3.2************************//*
                        paragraphOver = true;
                        length = 0;
                        paragraphReadedLen = 0;
                        //**************************//*
                        buffer.resetReaderIndex();
                        int readerIndex = buffer.readerIndex();
                        //*total length from '[' ('[' is included)*//*
                        //frameLength += length;
                        logger.debug("[{}]decode fail: state=STATE_SERIAL,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                        //*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*//*
                        buffer.readerIndex(readerIndex - frameLength + 1);

                        state = STATE_IDLE;
                    }
                    //**2************************//*
                    //处理半包数据
                    decodeHalfTcpPkg(buffer);
                    //**************************//*
                    break;

                case STATE_COMMAND:
                    /**1*******************/
                    paragraphTotalLen = 2;
                    /*********************/
                    byteCode = buffer.readByte();
                    length++;

                    //parse command.
                    if (byteCode == 0x2C) { /* ',' */
                        buffer.resetReaderIndex();
                        ByteBuf frame = buffer.readBytes(length - 1);


                        if (logger.isDebugEnabled()) {
                            Utils.dumpBuffer(frame);
                        }


                        buffer.skipBytes(1); /*skip field separator ','*/
                        buffer.markReaderIndex(); /*mark the next field start point*/

                        frameLength += length;
                        length = 0;

                        /**3.1****************************/
                        byte[] textBytes;
                        if (paragraphOver) {
                            textBytes = new byte[frame.readableBytes()];
                            frame.readBytes(textBytes);
                        } else {
                            frame.readBytes(paragraph, paragraphReadedLen, frame.readableBytes());
                            textBytes = paragraph;
                        }
                        paragraphOver = true;
                        paragraphReadedLen = 0;
                        /*****************************/
                        String text = new String(textBytes, "UTF-8");
                        logger.debug("[{}]decoded 报文类型: \"{}\".", sessionToken, text);
                        tMsg.setCmd(text);


                        state = STATE_TEXT;


                        int readerIndex = buffer.readerIndex();
                        logger.debug("[{}]decode command: state={},readerIndex={},frameLength={}.", new Object[]{sessionToken, state, readerIndex, frameLength});
                    } else if (length > 2) { //length of type is no more than 3.
                        /**3.2************************/
                        paragraphOver = true;
                        length = 0;
                        paragraphReadedLen = 0;
                        /**************************/
                        buffer.resetReaderIndex();
                        int readerIndex = buffer.readerIndex();
                        /*total length from '[' ('[' is included)*/
                        //frameLength += length;
                        logger.debug("[{}]decode fail: state=STATE_COMMAND,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                        /*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
                        buffer.readerIndex(readerIndex - frameLength + 1);

                        state = STATE_IDLE;
                    }
                    /**2************************/
                    //处理半包数据
                    decodeHalfTcpPkg(buffer);
                    /**************************/
                    break;



                case STATE_TEXT:
                    /**1*******************/
                    paragraphTotalLen = 512;
                    /*********************/
                    byteCode = buffer.readByte();
                    length++;

                    //parse command.
                    if (byteCode == 0x2C) { /* ',' */
                        buffer.resetReaderIndex();
                        ByteBuf frame = buffer.readBytes(length - 1);


                        if (logger.isDebugEnabled()) {
                            Utils.dumpBuffer(frame);
                        }


                        buffer.skipBytes(1); /*skip field separator ','*/
                        buffer.markReaderIndex(); /*mark the next field start point*/

                        frameLength += length;
                        length = 0;

                        /**3.1****************************/
                        byte[] textBytes;
                        if (paragraphOver) {
                            textBytes = new byte[frame.readableBytes()];
                            frame.readBytes(textBytes);
                        } else {
                            frame.readBytes(paragraph, paragraphReadedLen, frame.readableBytes());
                            textBytes = paragraph;
                        }
                        paragraphOver = true;
                        paragraphReadedLen = 0;
                        /*****************************/
                        String text = new String(textBytes, "UTF-8");
                        logger.debug("[{}]decoded 报文体: \"{}\".", sessionToken, text);
                        String body = text.replace("[", "").replace("]", "");
                        tMsg.setBody(body);

                        state = STATE_TIME;

                        int readerIndex = buffer.readerIndex();
                        logger.debug("[{}]decode command: state={},readerIndex={},frameLength={}.", new Object[]{sessionToken, state, readerIndex, frameLength});
                    } else if (length > 512) { //length of type is no more than 3.
                        /**3.2************************/
                        paragraphOver = true;
                        length = 0;
                        paragraphReadedLen = 0;
                        /**************************/
                        buffer.resetReaderIndex();
                        int readerIndex = buffer.readerIndex();
                        /*total length from '[' ('[' is included)*/
                        //frameLength += length;
                        logger.debug("[{}]decode fail: state=STATE_COMMAND,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                        /*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
                        buffer.readerIndex(readerIndex - frameLength + 1);

                        state = STATE_IDLE;
                    }
                    /**2************************/
                    //处理半包数据
                    decodeHalfTcpPkg(buffer);
                    /**************************/
                    break;


                case STATE_TIME:
                    /**1*******************/
                    paragraphTotalLen = 14;
                    /*********************/
                    length++;

                    byteCode = buffer.readByte();
                    //parse frame command body.
                    if (byteCode == 0x5D) { /* ']' */
                        buffer.resetReaderIndex();
                        ByteBuf frame = buffer.readBytes(length-1);


                        if (logger.isDebugEnabled()) {
                            Utils.dumpBuffer(frame);
                        }
                        //buffer.skipBytes(1); /*skip field separator ','*/
                        buffer.markReaderIndex(); /*mark the next field start point*/

                        frameLength += (length - 1);
                        length = 0;

                        /**3.1****************************/
                        byte[] textBytes;
                        if (paragraphOver) {
                            textBytes = new byte[frame.readableBytes()];
                            frame.readBytes(textBytes);
                        } else {
                            frame.readBytes(paragraph, paragraphReadedLen, frame.readableBytes());
                            textBytes = new byte[paragraphReadedLen + length];
                            System.arraycopy(paragraph, 0, textBytes, 0, paragraphReadedLen + length);
                        }
                        paragraphOver = true;
                        paragraphReadedLen = 0;
                        /*****************************/
                        String text = new String(textBytes, "UTF-8");
                        logger.debug("[{}]decoded 发报时间: \"{}\".", sessionToken, text);
                        tMsg.setMasssgeTime(text);

                        state = STATE_TAIL;

                        int readerIndex = buffer.readerIndex();
                        logger.debug("[{}]decode command body: state=STATE_TAIL,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                    } else if (length > 15) { //command body length limits is 512.
                        /**3.2************************/
                        paragraphOver = true;
                        length = 0;
                        paragraphReadedLen = 0;
                        /**************************/
                        //error: no frame footer
                        buffer.resetReaderIndex();
                        int readerIndex = buffer.readerIndex();
                        /*total length from '[' ('[' is included)*/
                        //frameLength += length;
                        logger.debug("[{}]decode fail: state=STATE_TEXT,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                        /*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
                        buffer.readerIndex(readerIndex - frameLength + 1);

                        state = STATE_IDLE;
                    }
                    /**2************************/
                    //处理半包数据
                    decodeHalfTcpPkg(buffer);
                    /**************************/
                    break;

                case STATE_TAIL:
                    byteCode = buffer.readByte();
                    length++;

                    //parse frame footer.
                    if (byteCode == 0x5D) { /* ']' */
                        buffer.resetReaderIndex();
                        @SuppressWarnings("unused")
                        ByteBuf frame = buffer.readBytes(length);


                        if (logger.isDebugEnabled()) {
                            Utils.dumpBuffer(frame);
                        }

                        frameLength += length;

                        int readerIndex = buffer.readerIndex();
                        logger.debug("[{}]decoded tail: state=STATE_TAIL,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});

                        length = 0;
                        frameLength = 0;
                        dataLength = 0;

                        state = STATE_IDLE;

                        logger.debug("[{}]decode tail: state=STATE_IDLE,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});

                        out.add(tMsg);

                        String ip = ctx.channel().remoteAddress().toString();


                        //接收完数据包后释放buffer
                        //ReferenceCountUtil.release(buffer);
                        //记录接收数据包所消耗的时间
                        logger.debug("========================parse time: " + (System.currentTimeMillis() - beginTime) + " ms !!!!!========================");


                    } else if (length >= 1) { //the footer ']' length is exactly 1.
                        //error: no frame footer
                        buffer.resetReaderIndex();
                        int readerIndex = buffer.readerIndex();
                        /*total length from '[' ('[' is included)*/
                        //frameLength += length;
                        logger.debug("[{}]decode fail: state=STATE_MEDIA,readerIndex={},frameLength={}.", new Object[]{sessionToken, readerIndex, frameLength});
                        /*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
                        buffer.readerIndex(readerIndex - frameLength + 1);

                        state = STATE_IDLE;
                    }

                    break;

                default:
                    logger.error(String.format("[%]Should not come here: state %d", sessionToken, state));
                    ByteBuf frame = buffer.readBytes(buffer.readableBytes());

                    if (logger.isDebugEnabled()) {
                        Utils.dumpBuffer(frame);
                    }
                    break;
            }
        }
        //缓存复用
        //buffer.discardReadBytes();
        if (tMsg != null) {
            //logger.debug("device msg:" + JSONObject.fromObject(tMsg).toString());
        }
        logger.debug("********************ByteBuf hashcode is :" + buffer.hashCode() + "*********************");
    }

    private void decodeHalfTcpPkg(ByteBuf buffer) {
        if (!buffer.isReadable() && (paragraphReadedLen + length < paragraphTotalLen)) {
            logger.debug("half TCP pkg decoding ......");
            //如果到了一个TCP包的没结尾还获取完该字段的所有字节，则先暂存已经读取到的该字段的所有信息
            buffer.resetReaderIndex();
            ByteBuf frame = buffer.readBytes(length);//不用再标记了，已经读到TCP包的结尾了
            paragraphOver = false;
            if (paragraphReadedLen == 0) {
                paragraph = new byte[frame.readableBytes()];
                frame.readBytes(paragraph);
            } else {//网络情况差，或者该字段长度过大
                frame.readBytes(paragraph, paragraphReadedLen, frame.readableBytes());
            }
            logger.debug("length:{},paragraphReadedLen:{},paragraphTotalLen:{}",
                    new Object[]{length, paragraphReadedLen, paragraphTotalLen});
            length = 0;
            paragraphReadedLen += length;
        }

    }

    private byte getCheckSum(byte[] data) {
        byte sum = 0;
        for (int i = 0; i < data.length - 1; i++) {
            sum += data[i];
        }
        return sum;
    }

}
