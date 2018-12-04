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
package dudu.service.core;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.jms.*;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import dudu.service.core.utils.Utils;

public class SimpleMessageHandler {

	private Logger LOG=null;

	private Serializable objectMsg;
	protected HashMap<String, JmsTemplate> templateMap;
	private static AtomicLong peerMsgId = new AtomicLong(1);
	protected SimpleDateFormat sdfMilli;

	public SimpleMessageHandler(Logger logger) {
		this.LOG = logger;
		this.sdfMilli = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
	}

	public Logger getLogger() {
		return LOG;
	}

	public void setLogger(Logger logger) {
		this.LOG = logger;
	}

	public HashMap<String, JmsTemplate> getTemplateMap() {
		return templateMap;
	}

	public void setTemplateMap(HashMap<String, JmsTemplate> templateMap) {
		this.templateMap = templateMap;
	}

	protected long createPeerMsgId() {
		return peerMsgId.getAndIncrement();
	}

	public void sendMessage(Long msgId,
		Object message,
		String msgType,
		String msgToken,
		String to,
		String destId,
		String from,
		String srcId) throws MessageException {

		LOG.debug("destination: {}, {}.", to, destId);

		JmsTemplate producer = null;
		JmsTemplate monitor = null;
		String type = msgType.substring(msgType.indexOf('/') + 1);

		if (templateMap.containsKey(type)) {
			producer = templateMap.get(type);
		} else {
			producer = templateMap.get("default");
		}

		if (templateMap.containsKey("monitor")) {
			monitor = templateMap.get("monitor");
		}

		if (producer == null) {
			LOG.warn("No message producer for type: {}.", type);
		}

		if (producer == null && monitor == null) {
			return;
		}

		PeerMessage sendMsg = new PeerMessage();
		sendMsg.setMsgId(msgId.toString());
		sendMsg.setType(msgType);
		sendMsg.setToken(msgToken);
		sendMsg.setData(message);
		sendMsg.setTo(to);
		sendMsg.setDestId(destId);
		sendMsg.setFrom(from);
		sendMsg.setSrcId(srcId);

		Date utcTime = Utils.getUTCTime();
		sendMsg.setTimestamp(this.sdfMilli.format(utcTime));

		if (LOG.isDebugEnabled()) {
			String text = JSONObject.fromObject(sendMsg).toString();
			LOG.debug(text);
		}

		objectMsg = sendMsg;
		try {
			if (producer != null) {
				producer.send(new MessageCreator() {
		            public Message createMessage(Session session) throws JMSException {
		                ObjectMessage jmsMessage = session.createObjectMessage(objectMsg);

		                return jmsMessage;
		            }
		        });
			}

			if (monitor != null) {
				monitor.send(new MessageCreator() {
		            public Message createMessage(Session session) throws JMSException {
		            	ObjectMessage jmsMessage = session.createObjectMessage(objectMsg);

		                return jmsMessage;
		            }
		        });
			}
		} catch (JmsException e) {
			throw new MessageException(e);
		}
	}

	public void sendMessage(Object message,
		String msgType,
		String msgToken,
		String to,
		String destId,
		String from,
		String srcId) throws MessageException {

		sendMessage(
			createPeerMsgId(),
			message,
			msgType,
			msgToken,
			to,
			destId,
			from,
			srcId);
	}

	public void sendMessage(String msgType,String jsonObject) throws MessageException {

		LOG.debug("destination: {}, {}.", msgType, jsonObject);

		JmsTemplate producer = null;
		JmsTemplate monitor = null;
		String type = msgType.substring(msgType.indexOf('/') + 1);

		if (templateMap.containsKey(type)) {
			producer = templateMap.get(type);
		} else {
			producer = templateMap.get("default");
		}


		if (producer == null) {
			LOG.warn("No message producer for type: {}.", type);
		}

		if (producer == null && monitor == null) {
			return;
		}

		PeerMessage sendMsg = new PeerMessage();
		sendMsg.setType(msgType);


		Date utcTime = Utils.getUTCTime();
		sendMsg.setTimestamp(this.sdfMilli.format(utcTime));

		if (LOG.isDebugEnabled()) {
			String text = JSONObject.fromObject(sendMsg).toString();
			LOG.debug(text);
		}
		try {
			if (producer != null) {
				producer.send(new MessageCreator() {
					public Message createMessage(Session session) throws JMSException {
						TextMessage textMessage = session.createTextMessage(jsonObject);

						return textMessage;
					}
				});
			}
		} catch (JmsException e) {
			throw new MessageException(e);
		}
	}
}
