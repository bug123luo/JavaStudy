package com.roger.activemq;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.apache.commons.lang.StringUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQAdapter implements IActiveMQAdapter {
	
	@Resource(name="jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;
	@Resource(name="jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;

	public JmsTemplate getJmsQueueTemplate() {
		// TODO Auto-generated method stub
		return null;
	}
	public JmsTemplate getJmsTopicTemplate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 指定消息发送的目标（主题），发送object消息（包括对象/文本）
	 * 
	 * @param destinationName
	 *            消息目标（主题）
	 * @param objectMessage
	 *            object消息
	 * @param flag
	 *            队列发送（false）/订阅（广播）发送（true）
	 */
	public void sendObjectMessage(String destinationName, Object objectMessage, boolean flag) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(destinationName) && objectMessage!=null) {
			if (flag) {
				// 订阅（广播）发送
				jmsTopicTemplate.convertAndSend(destinationName, objectMessage);
			} else {
				// 队列发送
				jmsQueueTemplate.convertAndSend(destinationName,objectMessage);
			}
		}
	}
	/**
	 * 指定消息发送的目标（主题），发送object消息（包括对象/文本）
	 * 
	 * @param destination
	 *            消息目标（主题）对象
	 * @param objectMessage
	 *            object消息
	 * @param flag
	 *            队列发送（false）/订阅（广播）发送（true）
	 * @param messagePostProcessor
	 * 			     消息后置处理器，可以给objectMessage添加属性
	 */
	public void sendObjectMessageProcessor(String destinationName, Object objectMessage,boolean flag,MessagePostProcessor messagePostProcessor){
		if (StringUtils.isNotBlank(destinationName) && objectMessage!=null&&messagePostProcessor!=null) { 
			if (flag) {
				// 订阅（广播）发送
				jmsTopicTemplate.convertAndSend(destinationName, objectMessage,messagePostProcessor);
			} else {
				// 队列发送
				jmsQueueTemplate.convertAndSend(destinationName,objectMessage,messagePostProcessor);
			}
		}
	}
	
	/**
	 * 指定消息发送的目标（主题），发送object消息（包括对象/文本）
	 * 
	 * @param destination
	 *            消息目标（主题）对象
	 * @param objectMessage
	 *            object消息
	 * @param flag
	 *            队列发送（false）/订阅（广播）发送（true）
	 */
	public void sendObjectMessage(Destination destination, Object objectMessage, boolean flag) {
		// TODO Auto-generated method stub
		if (destination!=null && objectMessage!=null) {
			if (flag) {
				// 订阅（广播）发送
				jmsTopicTemplate.convertAndSend(destination, objectMessage);
			} else {
				// 队列发送
				jmsQueueTemplate.convertAndSend(destination,objectMessage);
			}
		}
	}
	
	/**
	 * 指定消息发送的目标（主题），发送object消息（包括对象/文本）
	 * 
	 * @param destination
	 *            消息目标（主题）对象
	 * @param objectMessage
	 *            object消息
	 * @param flag
	 *            队列发送（false）/订阅（广播）发送（true）
	 * @param messagePostProcessor
	 * 			     消息后置处理器，可以给objectMessage添加属性
	 */
	public void sendObjectMessageProcessor(Destination destination, Object objectMessage,boolean flag,MessagePostProcessor messagePostProcessor){
		if (destination!=null && objectMessage!=null&&messagePostProcessor!=null) { 
			if (flag) {
				// 订阅（广播）发送
				jmsTopicTemplate.convertAndSend(destination, objectMessage,messagePostProcessor);
			} else {
				// 队列发送
				jmsQueueTemplate.convertAndSend(destination,objectMessage,messagePostProcessor);
			}
		}
	}
	/**
	 * 默认消息发送目标（主题），发送object消息（包括对象/文本）
	 * 
	 * @param objectMessage
	 *            object消息
	 * @param flag
	 *            队列发送（false）/订阅（广播）发送（true）
	 */
	public void sendObjectMessage(Object objectMessage, boolean flag) {
		// TODO Auto-generated method stub
		if (objectMessage!=null) {
			if (flag) {
				// 订阅（广播）发送
				jmsTopicTemplate.convertAndSend(objectMessage);
			} else {
				// 队列发送
				jmsQueueTemplate.convertAndSend(objectMessage);
			}
		}
	}
	/**
	 * 默认消息发送的目标（主题），发送object消息（包括对象/文本）
	 *
	 * @param objectMessage
	 *            object消息
	 * @param flag
	 *            队列发送（false）/订阅（广播）发送（true）
	 * @param messagePostProcessor
	 * 			     消息后置处理器，可以给objectMessage添加属性
	 */
	public void sendObjectMessageProcessor(Object objectMessage,boolean flag,MessagePostProcessor messagePostProcessor){
		if (objectMessage!=null&&messagePostProcessor!=null) { 
			if (flag) {
				// 订阅（广播）发送
				jmsTopicTemplate.convertAndSend(objectMessage,messagePostProcessor);
			} else {
				// 队列发送
				jmsQueueTemplate.convertAndSend(objectMessage,messagePostProcessor);
			}
		}
	}
}
