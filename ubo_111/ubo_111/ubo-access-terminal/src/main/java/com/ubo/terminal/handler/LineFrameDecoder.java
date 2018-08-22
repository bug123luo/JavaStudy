package com.ubo.terminal.handler;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.common.terminal.UboTerminalMessage;

import dudu.service.core.utils.Utils;

public class LineFrameDecoder extends MessageToMessageDecoder<ByteBuf> {
	
	private static final Logger LOG=
		LoggerFactory.getLogger(LineFrameDecoder.class);
	
	public LineFrameDecoder() {
		
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
		List<Object> out) throws Exception {

		LOG.debug("decode()");
		
		ByteBuf buffer = msg;
		
		//Make sure if the command field was received.
	    if (buffer.readableBytes() < 6) {
	        // The command field was not received yet - return null.
	        // This method will be invoked again when more packets are
	        // received and appended to the buffer.
	        return;
	    }

	    //The command field is in the buffer.
	    while (buffer.isReadable()) {
	    	byte head = buffer.readByte();
	    	if (head == 0x5B) { /*'['*/
	    		int readerIndex = buffer.readerIndex();
	    		LOG.debug("readerIndex: {}.", readerIndex-1);
	    		buffer.readerIndex(readerIndex - 1);
	    		buffer.markReaderIndex();
	    		int length = 0;
    			while (buffer.isReadable()) {
    				byte tail = buffer.readByte();
    				length++;
    				if (tail == 0x5D) { /*']'*/
    					LOG.debug("length: {}.", length);
    					buffer.resetReaderIndex();
    					ByteBuf frame = buffer.readBytes(length);
    					if (LOG.isDebugEnabled()) {
    						Utils.dumpBuffer(frame);
    					}
    					
    					byte[] textBytes = new byte[frame.readableBytes()];
    					frame.readBytes(textBytes);
        				String text = new String(textBytes, "UTF-8");
        				LOG.debug("decoded text: \"{}\".", text);
        				
        				String content = text.substring(1, text.length() - 1);
        				String[] parts = content.split(",");
        				
    					if (parts.length == 4) {
    						UboTerminalMessage tMsg = new UboTerminalMessage();
    						tMsg.setTid(parts[0]);
    						tMsg.setSerial(parts[1]);
    						tMsg.setCmd(parts[2]);
    						tMsg.setBody(parts[3]);
    						
    						out.add(tMsg);
    					} else {
    						LOG.error("Unparsable message: " + text);
    						// 将数据传到下一个 channel handler
    		    			ctx.fireChannelRead(msg);
    					}
        				
        				return;
    				}
    				
    			}	
    		} else {
    			//skip unknown data
    			LOG.warn(String.format("Unknow data: \'0x%x\'", head));
    			
    			// 将数据传到下一个 channel handler
    			ctx.fireChannelRead(msg);
    		}
	    }
	}
	
}
