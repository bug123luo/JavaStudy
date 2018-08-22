/*
 * Copyright 2015 The Dudu Project
 *
 * The Dudu Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package dudu.service.core.processor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import dudu.service.core.MessageBean;
import dudu.service.core.MessageDecoder;
import dudu.service.core.MessageHandler;
import dudu.service.core.PeerMessage;
import dudu.service.core.utils.Utils;

public class ProcessorMessageListener implements MessageListener {
		
	private static final Logger LOG=LoggerFactory.getLogger(ProcessorMessageListener.class);
	
	private HashMap<String, MessageDecoder> decoderMap;
	private HashMap<String, MessageHandler> handlerMap;
	
	public ProcessorMessageListener(
		HashMap<String, MessageDecoder> decoderMap,
		HashMap<String, MessageHandler> handlerMap) {
		
		this.decoderMap = decoderMap;
		this.handlerMap = handlerMap;
	}
	    
    public void onMessage(Message message) {
    	
    	ObjectMessage objectMsg = (ObjectMessage)message;
    	
    	do {
	    	if (!(objectMsg instanceof ObjectMessage)) {
	    		LOG.debug("onMessage(clazz={})", message.getClass().getName());
	    		LOG.error("Invalid ObjectMessage!");
	    		break;
	    	}
        
	        try {
	        	
	        	Object data = objectMsg.getObject();
	        	if (!(data instanceof PeerMessage)) {
	        		LOG.debug("data(clazz={})", data.getClass().getName());
	        		LOG.error("Invalid PeerMessage!");
	        		break;
	        	}
	        	
	        	PeerMessage peerMsg = (PeerMessage)data;
	        	
	        	if (LOG.isDebugEnabled()) {
	        		SimpleDateFormat sdfMilli = 
	        			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
	        		Date sendTime = sdfMilli.parse(peerMsg.getTimestamp());
	        		Calendar cal = Calendar.getInstance();
	        		long transmitTime = cal.getTimeInMillis() 
	        			- cal.get(Calendar.ZONE_OFFSET) 
	        			- sendTime.getTime();
	        		LOG.debug("transmission takes time: {}.", transmitTime);
	        	}
	        	
	        	if (LOG.isDebugEnabled()) {
	        		String text = JSONObject.fromObject(data).toString();
		            LOG.debug(text);
	        	}
	            
	            String type = peerMsg.getType();
	            LOG.debug("type: {}.", type);
	            
	            String dataType = type.substring(type.indexOf('/') + 1);
	            LOG.debug("data type: {}.", dataType);
	            
	            LOG.info("message is from \"{}\", \"{}\"",
	            	peerMsg.getFrom(),
	            	peerMsg.getSrcId());
	            
	            data = peerMsg.getData();
	            LOG.debug("data class name: {}.", data.getClass().getName());
	            
	            long timeMark = System.currentTimeMillis();
	            
	            Object decodedMsg = null;
	            if (!type.contains("plain") && !type.contains("binary")) {
	            	decodedMsg = data;
	            }
	            
	            if (decodedMsg == null && decoderMap.containsKey(dataType)) {
	            	MessageDecoder decoder = 
						(MessageDecoder)decoderMap.get(dataType);
					
	            	LOG.debug("decoding...");
					if (data instanceof String) {
						decodedMsg = (MessageBean)decoder.decode((String)data);
					} else if (data instanceof byte[]) {
						decodedMsg = (MessageBean)decoder.decode((byte[])data);
					} else {
						LOG.error("unkown data type: {}.", data.getClass().getName());
						break;
					}
				}
	            
	            if (LOG.isInfoEnabled()) {
	            	long decodeTime = System.currentTimeMillis() - timeMark;
	            	LOG.info("decoder takes time: {}.", decodeTime);
	            }
	            
	            timeMark = System.currentTimeMillis();
	            
	            if (decodedMsg != null && handlerMap.containsKey(dataType)) {
	            	MessageHandler handler = (MessageHandler)handlerMap.get(dataType);
	            	peerMsg.setData(decodedMsg);
	            	
	            	LOG.debug("handling...");
	            	handler.handleMessage(peerMsg);
	            } else {
	            	//drop this message
	            	LOG.info("!!!No handler, dropped: ({})", type);
	            }
	            
	            if (LOG.isInfoEnabled()) {
	            	long handleTime = System.currentTimeMillis() - timeMark;
	            	LOG.info("handler takes time: {}.", handleTime);
	            }
	            
	        } catch (JMSException e) {
	        	LOG.error(Utils.getThrowableInfo(e));
	            //e.printStackTrace();
	        } catch (Exception e) {
	        	LOG.error(Utils.getThrowableInfo(e));
	            //e.printStackTrace();
	        }
	        
    	} while (false);
    }
    
} 
