//package com.ubo.terminal.handler;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.ubo.common.terminal.CommandType;
//import com.ubo.common.terminal.UboCommandMessage;
//import com.ubo.common.terminal.UboTerminalMessage;
//
//import dudu.service.core.utils.Utils;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelFutureListener;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.MessageToMessageDecoder;
//
//public class MixFrameDecoder_bak extends MessageToMessageDecoder<ByteBuf> {
//
//	private static final Logger LOG=
//		LoggerFactory.getLogger(MixFrameDecoder_bak.class);
//
//	private int state;
//	private int length;
//	private int dataLength;
//	private int frameLength;
//	private UboTerminalMessage tMsg;
//
//	private ByteBuf lastByteBuf;
//
//	public final static int STATE_IDLE = 0;
//	public final static int STATE_ID = 1;
//	public final static int STATE_SERIAL = 2;
//	public final static int STATE_COMMAND = 3;
//	public final static int STATE_SUBCOMMAND = 4;
//	public final static int STATE_ZIP = 5;
//	public final static int STATE_TOTALPKGNO = 6;
//	public final static int STATE_PKGNO = 7;
//	public final static int STATE_PKGLEN = 8;
//	public final static int STATE_MEDIA = 9;
//	public final static int STATE_TEXT = 10;
//	public final static int STATE_TAIL = 11;
//
//	public MixFrameDecoder_bak() {
//		state = STATE_IDLE;
//	}
//
//	@Override
//	protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
//		List<Object> out) throws Exception {
//
//		LOG.debug("decode()");
//		LOG.debug(String.format("buffer.readableBytes: \'0x%x\'", msg.readableBytes()));
//
//		if (lastByteBuf == null) {
//			lastByteBuf = msg;
//		} else {
//			LOG.debug("LastByteBuf == NewByteBuf?" + (lastByteBuf == msg));
//			lastByteBuf = msg;
//		}
//
//		ByteBuf buffer = msg;
//
//		//Make sure if the command field was received.
//	    if (buffer.readableBytes() < 40) {
//	        // The command field was not received yet - return null.
//	        // This method will be invoked again when more packets are
//	        // received and appended to the buffer.
//	        return;
//	    }
//
//		if (LOG.isDebugEnabled()) {
//			Utils.dumpBuffer2(buffer);
//		}
//
//	    /**
//	     * 51 message
//	     * [imei or terminal_id,serial_no,type,sub_type,zip_flag,total_package,package_no,length,data]
//	     *
//	     * other message
//	     * [imei or terminal_id(15),serial_no(18),type(2),data(1+)]
//	     *
//	     * length: field length
//	     * frameLength: raw message frame length
//	     * dataLength:
//	     *
//	     * frameLength >= 40
//	     *
//	     * */
//
//	    LOG.debug("decoder state: " + state);
//
//	    byte byteCode;
//
//	    while (buffer.isReadable()) {
//
//	    	switch(state) {
//	    	case STATE_IDLE:
//	    		byteCode = buffer.readByte();
//
//	    		//pick up the frame header.
//		    	if (byteCode == 0x5B) { /*'['*/
//		    		int readerIndex = buffer.readerIndex();
//		    		//LOG.debug("readerIndex: {}.", readerIndex);
//		    		buffer.markReaderIndex(); /*mark next field start point*/
//
//		    		length = 0;
//		    		frameLength = 1;
//
//		    		tMsg = new UboTerminalMessage();
//
//		    		state = STATE_ID;
//
//		    		LOG.debug("decoded frame header: state=STATE_ID,readerIndex={},frameLength={}.", readerIndex, frameLength);
//		    	} else {
//		    		//skip unknown data
//	    			LOG.warn(String.format("STATE_IDLE::Unknow data: \'0x%x\'", byteCode));
//		    	}
//		    	break;
//
//	    	case STATE_ID:
//	    		byteCode = buffer.readByte();
//	    		length++;
//
//	    		//parse terminal id
//	    		if (byteCode == 0x2C) { /* ',' */
//	    			buffer.resetReaderIndex();
//					ByteBuf frame = buffer.readBytes(length - 1);
//
//
//					if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//
//
//    				buffer.skipBytes(1); /*skip field separator ','*/
//    				buffer.markReaderIndex(); /*mark the next field start point*/
//
//    				frameLength += length;
//    				length = 0;
//
//    				byte[] textBytes = new byte[frame.readableBytes()];
//					frame.readBytes(textBytes);
//    				String text = new String(textBytes, "UTF-8");
//    				LOG.debug("decoded terminal id: \"{}\".", text);
//    				tMsg.setTid(text);
//
//    				state = STATE_SERIAL;
//
//    				int readerIndex = buffer.readerIndex();
//    				LOG.debug("decode terminal id: state=STATE_SERIAL,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    		} else if (length > 20) { //length of imei or terminal id is less than 18.
//	    			buffer.resetReaderIndex();
//	    			int readerIndex = buffer.readerIndex();
//	    			/*total length from '[' ('[' is included)*/
//	    			//frameLength += length;
//	    			//回滚到STATE_ID解析前的readerIndex和frameLength
//	    			LOG.debug("decode fail: state=STATE_ID,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    			/*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
//	    			buffer.readerIndex(readerIndex - frameLength + 1);
//
//	    			state = STATE_IDLE;
//	    		}
//	    		break;
//
//	    	case STATE_SERIAL:
//	    		byteCode = buffer.readByte();
//	    		length++;
//
//	    		//parse serial NO.
//	    		if (byteCode == 0x2C) { /* ',' */
//	    			buffer.resetReaderIndex();
//					ByteBuf frame = buffer.readBytes(length - 1);
//
//
//					if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//
//
//					buffer.skipBytes(1); /*skip field separator ','*/
//    				buffer.markReaderIndex(); /*mark the next field start point*/
//
//    				frameLength += length;
//    				length = 0;
//
//					byte[] textBytes = new byte[frame.readableBytes()];
//					frame.readBytes(textBytes);
//    				String text = new String(textBytes, "UTF-8");
//    				LOG.debug("decoded serial NO: \"{}\".", text);
//    				tMsg.setSerial(text);
//
//    				state = STATE_COMMAND;
//
//    				int readerIndex = buffer.readerIndex();
//    				LOG.debug("decode serial NO: state=STATE_COMMAND,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    		} else if (length > 20) { //length of serial NO is 18.
//	    			buffer.resetReaderIndex();
//	    			int readerIndex = buffer.readerIndex();
//	    			/*total length from '[' ('[' is included)*/
//	    			//frameLength += length;
//	    			LOG.debug("decode fail: state=STATE_SERIAL,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    			/*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
//	    			buffer.readerIndex(readerIndex - frameLength + 1);
//
//	    			state = STATE_IDLE;
//	    		}
//	    		break;
//
//	    	case STATE_COMMAND:
//	    		byteCode = buffer.readByte();
//	    		length++;
//
//	    		//parse command.
//	    		if (byteCode == 0x2C) { /* ',' */
//	    			buffer.resetReaderIndex();
//					ByteBuf frame = buffer.readBytes(length - 1);
//
//
//					if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//
//
//					buffer.skipBytes(1); /*skip field separator ','*/
//    				buffer.markReaderIndex(); /*mark the next field start point*/
//
//    				frameLength += length;
//    				length = 0;
//
//					byte[] textBytes = new byte[frame.readableBytes()];
//					frame.readBytes(textBytes);
//    				String text = new String(textBytes, "UTF-8");
//    				LOG.debug("decoded command: \"{}\".", text);
//    				tMsg.setCmd(text);
//
//    				if (text.equals("51")) { //media data
//    					state = STATE_SUBCOMMAND;
//    				} else {
//    					state = STATE_TEXT;
//    				}
//
//    				int readerIndex = buffer.readerIndex();
//    				LOG.debug("decode command: state={},readerIndex={},frameLength={}.", new Object[] {state, readerIndex, frameLength});
//	    		} else if (length > 4) { //length of type is no more than 3.
//	    			buffer.resetReaderIndex();
//	    			int readerIndex = buffer.readerIndex();
//	    			/*total length from '[' ('[' is included)*/
//	    			//frameLength += length;
//	    			LOG.debug("decode fail: state=STATE_COMMAND,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    			/*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
//	    			buffer.readerIndex(readerIndex - frameLength + 1);
//
//	    			state = STATE_IDLE;
//	    		}
//	    		break;
//
//	    	case STATE_TEXT:
//	    		byteCode = buffer.readByte();
//	    		length++;
//
//	    		//parse frame command body.
//	    		if (byteCode == 0x5D) { /* ']' */
//	    			buffer.resetReaderIndex();
//	    			ByteBuf frame = buffer.readBytes(length - 1);
//
//
//	    			if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//
//
//	    			//buffer.skipBytes(1); /*skip field separator ','*/
//    				buffer.markReaderIndex(); /*mark the next field start point*/
//
//    				frameLength += (length - 1);
//    				length = 0;
//
//	    			byte[] textBytes = new byte[frame.readableBytes()];
//					frame.readBytes(textBytes);
//    				String text = new String(textBytes, "UTF-8");
//    				LOG.debug("decoded command body: \"{}\".", text);
//    				tMsg.setBody(text);
//
//	    			state = STATE_TAIL;
//
//	    			int readerIndex = buffer.readerIndex();
//    				LOG.debug("decode command body: state=STATE_TAIL,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    		} else if (length > 512) { //command body length limits is 512.
//	    			//error: no frame footer
//	    			buffer.resetReaderIndex();
//	    			int readerIndex = buffer.readerIndex();
//	    			/*total length from '[' ('[' is included)*/
//	    			//frameLength += length;
//	    			LOG.debug("decode fail: state=STATE_TEXT,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    			/*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
//	    			buffer.readerIndex(readerIndex - frameLength + 1);
//
//	    			state = STATE_IDLE;
//	    		}
//	    		break;
//
//	    	case STATE_SUBCOMMAND:
//	    		byteCode = buffer.readByte();
//	    		length++;
//
//	    		//parse sub command.
//	    		if (byteCode == 0x2C) { /* ',' */
//	    			buffer.resetReaderIndex();
//					ByteBuf frame = buffer.readBytes(length - 1);
//
//
//					if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//
//
//					buffer.skipBytes(1); /*skip field separator ','*/
//    				buffer.markReaderIndex(); /*mark the next field start point*/
//
//    				frameLength += length;
//    				length = 0;
//
//					byte[] textBytes = new byte[frame.readableBytes()];
//					frame.readBytes(textBytes);
//    				String text = new String(textBytes, "UTF-8");
//    				LOG.debug("decoded sub command: \"{}\".", text);
//
//    				if ("0".equals(text)) {
//    					tMsg.setRecordType("sos");
//    				} else {
//    					tMsg.setRecordType("normal");
//    				}
//
//    				state = STATE_ZIP;
//
//    				int readerIndex = buffer.readerIndex();
//    				LOG.debug("decode sub command: state=STATE_ZIP,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    		} else if (length > 1) { //length of type is 1.
//	    			buffer.resetReaderIndex();
//	    			int readerIndex = buffer.readerIndex();
//	    			/*total length from '[' ('[' is included)*/
//	    			//frameLength += length;
//	    			LOG.debug("decode fail: state=STATE_SUBCOMMAND,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    			/*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
//	    			buffer.readerIndex(readerIndex - frameLength + 1);
//
//	    			state = STATE_IDLE;
//	    		}
//	    		break;
//
//	    	case STATE_ZIP:
//	    		byteCode = buffer.readByte();
//	    		length++;
//
//	    		//parse zip flag.
//	    		if (byteCode == 0x2C) { /* ',' */
//	    			buffer.resetReaderIndex();
//					ByteBuf frame = buffer.readBytes(length - 1);
//
//
//					if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//
//
//					buffer.skipBytes(1); /*skip field separator ','*/
//    				buffer.markReaderIndex(); /*mark the next field start point*/
//
//    				frameLength += length;
//    				length = 0;
//
//					byte[] textBytes = new byte[frame.readableBytes()];
//					frame.readBytes(textBytes);
//    				String text = new String(textBytes, "UTF-8");
//    				LOG.debug("decoded zip: \"{}\".", text);
//    				if (text.equals("1")) {
//    					tMsg.setZip(true);
//    				} else {
//    					tMsg.setZip(false);
//    				}
//
//    				state = STATE_TOTALPKGNO;
//
//    				int readerIndex = buffer.readerIndex();
//    				LOG.debug("decode zip: state=STATE_TOTALPKGNO,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    		} else if (length > 1) { //length of zip flag is 1.
//	    			buffer.resetReaderIndex();
//	    			int readerIndex = buffer.readerIndex();
//	    			/*total length from '[' ('[' is included)*/
//	    			//frameLength += length;
//	    			LOG.debug("decode fail: state=STATE_ZIP,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    			/*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
//	    			buffer.readerIndex(readerIndex - frameLength + 1);
//
//	    			state = STATE_IDLE;
//	    		}
//	    		break;
//
//	    	case STATE_TOTALPKGNO:
//	    		byteCode = buffer.readByte();
//	    		length++;
//
//	    		//parse  total package number.
//	    		if (byteCode == 0x2C) { /* ',' */
//	    			buffer.resetReaderIndex();
//					ByteBuf frame = buffer.readBytes(length - 1);
//
//
//					if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//
//
//					buffer.skipBytes(1); /*skip field separator ','*/
//    				buffer.markReaderIndex(); /*mark the next field start point*/
//
//    				frameLength += length;
//    				length = 0;
//
//					byte[] textBytes = new byte[frame.readableBytes()];
//					frame.readBytes(textBytes);
//    				String text = new String(textBytes, "UTF-8");
//    				LOG.debug("decoded total package NO: \"{}\".", text);
//
//    				tMsg.setTotalPkgNo(Integer.parseInt(text));
//
//    				state = STATE_PKGNO;
//
//    				int readerIndex = buffer.readerIndex();
//    				LOG.debug("decode total package NO: state=STATE_PKGNO,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    		} else if (length > 2) { //length of total package NO is 2.
//	    			buffer.resetReaderIndex();
//	    			int readerIndex = buffer.readerIndex();
//	    			/*total length from '[' ('[' is included)*/
//	    			//frameLength += length;
//	    			LOG.debug("decode fail: state=STATE_TOTALPKGNO,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    			/*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
//	    			buffer.readerIndex(readerIndex - frameLength + 1);
//
//	    			state = STATE_IDLE;
//	    		}
//
//	    		break;
//
//	    	case STATE_PKGNO:
//	    		byteCode = buffer.readByte();
//	    		length++;
//
//	    		//parse package number.
//	    		if (byteCode == 0x2C) { /* ',' */
//	    			buffer.resetReaderIndex();
//					ByteBuf frame = buffer.readBytes(length - 1);
//
//
//					if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//
//
//					buffer.skipBytes(1); /*skip field separator ','*/
//    				buffer.markReaderIndex(); /*mark the next field start point*/
//
//    				frameLength += length;
//    				length = 0;
//
//					byte[] textBytes = new byte[frame.readableBytes()];
//					frame.readBytes(textBytes);
//    				String text = new String(textBytes, "UTF-8");
//    				LOG.debug("decoded package number: \"{}\".", text);
//    				tMsg.setPkgNo(Integer.parseInt(text));
//
//    				state = STATE_PKGLEN;
//
//    				int readerIndex = buffer.readerIndex();
//    				LOG.debug("decode package NO: state=STATE_PKGLEN,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    		} else if (length > 3) { //length of type is 2.
//	    			buffer.resetReaderIndex();
//	    			int readerIndex = buffer.readerIndex();
//	    			/*total length from '[' ('[' is included)*/
//	    			//frameLength += length;
//	    			LOG.debug("decode fail: state=STATE_PKGNO,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    			/*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
//	    			buffer.readerIndex(readerIndex - frameLength + 1);
//
//	    			state = STATE_IDLE;
//	    		}
//	    		break;
//
//	    	case STATE_PKGLEN:
//	    		byteCode = buffer.readByte();
//	    		length++;
//
//	    		//parse data length.
//	    		if (byteCode == 0x2C) { /* ',' */
//	    			buffer.resetReaderIndex();
//					ByteBuf frame = buffer.readBytes(length - 1);
//
//
//					if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//
//
//					buffer.skipBytes(1); /*skip field separator ','*/
//    				buffer.markReaderIndex(); /*mark the next field start point*/
//
//    				frameLength += length;
//    				length = 0;
//
//					byte[] textBytes = new byte[frame.readableBytes()];
//					frame.readBytes(textBytes);
//					//text
//    				String text = new String(textBytes, "UTF-8");
//    				//hex
//    				dataLength = Integer.parseInt(text, 16);
//    				tMsg.setZipBodyLen(dataLength);
//
//    				LOG.debug("decoded media package length: \"{}\" --> \"{}\".", text, dataLength);
//
//    				byte[] data = new byte[dataLength];
//					tMsg.setZipBody(data);
//
//    				state = STATE_MEDIA;
//
//    				int readerIndex = buffer.readerIndex();
//    				LOG.debug("decode media package length: state=STATE_MEDIA,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    		} else if (length > 4) { //length of package length is 4.
//	    			buffer.resetReaderIndex();
//	    			int readerIndex = buffer.readerIndex();
//	    			/*total length from '[' ('[' is included)*/
//	    			//frameLength += length;
//	    			LOG.debug("decode fail: state=STATE_PKGLEN,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    			/*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
//	    			buffer.readerIndex(readerIndex - frameLength + 1);
//
//	    			state = STATE_IDLE;
//	    		}
//	    		break;
//
//	    	case STATE_MEDIA:
//
//	    		length = buffer.readableBytes();
//
//	    		//parse zip body
//	    		if (length >= dataLength) {
//					length = dataLength;
//
//					//一包的开始，将前面的markReaderIndex操作取消。
//					if (dataLength == tMsg.getZipBody().length) {
//						buffer.resetReaderIndex();
//					}
//	    			ByteBuf frame = buffer.readBytes(length);
//
//
//		    		if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//
//
//		    		buffer.markReaderIndex();
//
//		    		byte[] zipBody = tMsg.getZipBody();
//		    		frame.readBytes(zipBody, zipBody.length - dataLength, length);
//
//		    		frameLength += length;
//		    		dataLength -= length;
//
//		    		LOG.debug("segment length: {}, left dataLength: {}", length, dataLength);
//					length = 0;
//
//					//validate checksum
//					byte recCheckSum = tMsg.getZipBody()[tMsg.getZipBodyLen()-1];
//					byte calCheckSum = getCheckSum(tMsg.getZipBody());
//
//					LOG.debug(".recCheckSum={},calCheckSum={}.", recCheckSum, calCheckSum);
//
//					if (recCheckSum == calCheckSum) {
//    					state = STATE_TAIL;
//
//    					int readerIndex = buffer.readerIndex();
//    					LOG.debug("decode media: state=STATE_TAIL,readerIndex={},frameLength={}.", readerIndex, frameLength);
//    				} else {
//    					// 回退到这包数据的开始的位置(不回退不知道可行不，设置还是有可能出现异常）
//    					// buffer.readerIndex(lastMeidaReaderIndex);
//
//    					// 通知设备重发数据
//    					UboCommandMessage replyMsg = new UboCommandMessage(
//    						tMsg.getTid(),
//    						CommandType.TRECORD_RESP_COMMAND,
//    						 String.format("%d@%d@1", tMsg.getTotalPkgNo(), tMsg.getPkgNo()));
//    					replyMsg.setMsgId(tMsg.getSerial());
//
//    					ChannelFuture future = ctx.channel().write(replyMsg);
//
//    					future.addListener(new ChannelFutureListener() {
//    						public void operationComplete(ChannelFuture future) throws Exception {
//    							if (!future.isSuccess()) {
//    								LOG.error("Fail to replay record message!");
//    								LOG.error(Utils.getThrowableInfo(future.cause()));
//    							}
//    						}
//
//    					});
//
//    					length = 0;
//    	    			frameLength = 0;
//    	    			dataLength = 0;
//
//    	    			tMsg = null;
//
//    	    			state = STATE_IDLE;
//
//    				}
//	    		} else { //length < dataLength, media package is not pick up completely.
//
//	    			//一包的开始，将前面的markReaderIndex操作取消。
//	    			if (dataLength == tMsg.getZipBody().length) {
//						buffer.resetReaderIndex();
//					}
//
//	    			ByteBuf frame = buffer.readBytes(length);
//
//
//		    		if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//
//		    		//buffer.markReaderIndex();
//
//		    		byte[] zipBody = tMsg.getZipBody();
//		    		frame.readBytes(zipBody, zipBody.length - dataLength, length);
//
//		    		frameLength += length;
//		    		dataLength -= length;
//		    		LOG.debug("segment length: {}, left dataLength: {}", length, dataLength);
//	    		}
//
//	    		break;
//
//	    	case STATE_TAIL:
//	    		byteCode = buffer.readByte();
//	    		length++;
//
//	    		//parse frame footer.
//	    		if (byteCode == 0x5D) { /* ']' */
//	    			buffer.resetReaderIndex();
//
//	    			@SuppressWarnings("unused")
//					ByteBuf frame = buffer.readBytes(length);
//
//	    			/*
//					if (LOG.isDebugEnabled()) {
//						Utils.dumpBuffer(frame);
//					}
//					*/
//	    			frameLength += length;
//
//	    			int readerIndex = buffer.readerIndex();
//	    			LOG.debug("decoded tail: state=STATE_TAIL,readerIndex={},frameLength={}.", readerIndex, frameLength);
//
//	    			length = 0;
//	    			frameLength = 0;
//	    			dataLength = 0;
//
//	    			state = STATE_IDLE;
//
//					LOG.debug("decode tail: state=STATE_IDLE,readerIndex={},frameLength={}.", readerIndex, frameLength);
//
//	    			out.add(tMsg);
//
//	    			String ip = ctx.channel().remoteAddress().toString();
//
//	    			String encodeMsg = String.format("[%s,%s,%s,%s,tr=%s,tp=%d,pno=%d,plen=%d,ip=%s]",
//	    					tMsg.getTid(),
//	    					tMsg.getSerial(),
//	    					tMsg.getCmd(),
//	    					tMsg.getBody(),
//	    					tMsg.getRecordType(),
//	    					tMsg.getTotalPkgNo(),
//	    					tMsg.getPkgNo(),
//	    					tMsg.getZipBodyLen(),
//	    					ip);
//
//	    			LOG.debug(".decode text:{}.", encodeMsg);
//
//	    		} else if (length >= 1) { //the footer ']' length is exactly 1.
//	    			//error: no frame footer
//	    			buffer.resetReaderIndex();
//	    			int readerIndex = buffer.readerIndex();
//	    			/*total length from '[' ('[' is included)*/
//	    			//frameLength += length;
//	    			LOG.debug("decode fail: state=STATE_MEDIA,readerIndex={},frameLength={}.", readerIndex, frameLength);
//	    			/*plus 1 to skip '[', this is not a parsable frame, go to find the next '['*/
//	    			buffer.readerIndex(readerIndex - frameLength + 1);
//
//	    			state = STATE_IDLE;
//	    		}
//
//	    		break;
//
//	    	default:
//	    		LOG.error(String.format("Should not come here: state %d", state));
//	    		ByteBuf frame = buffer.readBytes(buffer.readableBytes());
//
//				if (LOG.isDebugEnabled()) {
//					Utils.dumpBuffer(frame);
//				}
//	    		break;
//	    	}
//	    }
//
//	    if (tMsg != null) {
//	    	//LOG.debug("device msg:" + JSONObject.fromObject(tMsg).toString());
//	    }
//
//	}
//
//	private byte getCheckSum(byte[] data) {
//		byte sum = 0;
//		for (int i=0; i<data.length-1; i++) {
//			sum += data[i];
//		}
//		return sum;
//	}
//
//}
