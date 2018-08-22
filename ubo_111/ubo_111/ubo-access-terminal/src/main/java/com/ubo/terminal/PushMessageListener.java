package com.ubo.terminal;

import java.util.concurrent.ConcurrentHashMap;

import javax.jms.*;

import com.ubo.common.terminal.UboTerminalMessage;
import dudu.service.pojo.SimpleMessage;
import dudu.service.pojo.UboSimpleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.common.terminal.UboCommandMessageEx;
import com.ubo.common.utils.RecordUtils;

import dudu.service.core.PeerMessage;
import dudu.service.core.terminal.BasicMessage;
import dudu.service.core.utils.Utils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPipeline;
import net.sf.json.JSONObject;

public class PushMessageListener implements MessageListener {
	
	private static final Logger LOG=
		LoggerFactory.getLogger(PushMessageListener.class);
	    
    public void onMessage(Message message) {
		TextMessage objectMsg = (TextMessage)message;
    	
    	do {
	    	if (!(objectMsg instanceof TextMessage)) {
	    		LOG.debug("onMessage(clazz={})", message.getClass().getName());
	    		LOG.error("Invalid ObjectMessage!");
	    		break;
	    	}
        
	        try {
				JSONObject jsonObject = JSONObject.fromObject(objectMsg.getText());
				UboSimpleMessage data =(UboSimpleMessage) JSONObject.toBean(jsonObject,UboSimpleMessage.class);
	        	
	        	if (LOG.isDebugEnabled()) {
	        		String text = JSONObject.fromObject(data).toString();
					LOG.debug(text);
				}
				String type =data.getMessageType();
	            LOG.debug("type: {}.", type);

	            //shutdown command
	            if (type.equals("command/SHUTDOWN")) {
	            	LOG.debug("Receive SHUTDOWN command, server will be terminated.");
	        		AccessServer.getInstance().terminate();
	        		break;
	            }

	            if (data == null) {
	        		LOG.error("peerMsg.data is null.");
	        		break;
	        	}
	            
	            LOG.debug("peerMsg.data(clazz={})", data.getClass().getName());

	            LOG.debug("to: {}.", data.getSessionToken());
	            
	            ConcurrentHashMap<String, Channel> channelMap = 
	            	AccessServer.getInstance().getChannelMap();
	            if (channelMap.containsKey(data.getSessionToken())) {
	            	Channel channel = 
	            		(Channel)channelMap.get(data.getSessionToken());
	            	
	            	ChannelPipeline pipeline = channel.pipeline();
	    			SessionChannelHandler.Session session = pipeline.get(SessionChannelHandler.class).getSession();
	            	
	            	String sessionToken = data.getSessionToken();
	            	//这里修正为不需要建立会话，直接发送
	            	if(sessionToken != null){
	            		if (!sessionToken.equals(session.getToken())) {
		            		LOG.warn("session tokens unmatch: " +
		            			"{}(message), {}(channel).",
		            			sessionToken,
		            			session.getToken());
		            		break;
		            	}
	            	}else{
	            		sessionToken=session.getToken();
	            	}	            	
	            	
	            	LOG.debug("session tokens match: {}(message), {}(channel).",
	            		sessionToken,
	            		session.getToken());
	            	
	            	if (type.equals("command/DISCONNECT")) {
	            		
	            		channel.close();
	            		break;
	            		
	            	} else {

		            	// ======================================================
		            	
		            	ChannelFuture future = channel.write(data);
	
		            	LOG.debug(".T send msg={}.", data);
		            	
		    			future.addListener(new ChannelFutureListener() {
		
							public void operationComplete(ChannelFuture future)
								throws Exception {
								
								if (!future.isSuccess()) {
									LOG.error("Fail to send down message!");
									LOG.error(
										Utils.getThrowableInfo(
											future.cause()));
						        	future.addListener(ChannelFutureListener.CLOSE);
								}
							}
		    				
		    			});
		    			
	            	}
	            	
	            } else {
	            	LOG.warn("No target matches push message, to: {}.",
							data.getSessionToken());
	            }
	            
	        } catch (JMSException e) {
	        	LOG.error(Utils.getThrowableInfo(e));
	        } catch (Exception e) {  	
	        	LOG.error(Utils.getThrowableInfo(e));
	        }
	        
        } while (false);
    }
    
}
