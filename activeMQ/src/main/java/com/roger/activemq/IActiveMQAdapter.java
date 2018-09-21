package com.roger.activemq;

import javax.jms.Destination;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

/**
 *  
 * 
 * @ClassName:IActiveMQAdapter
 * @Description : 消息队列接口
 * @author:Roger
 * @date:2017年12月2日  下午1:58:14
 * @param:<T>
 */
public interface IActiveMQAdapter {
	/**
	 * 获取jmsQueueTemplate
	 */
	public JmsTemplate getJmsQueueTemplate();
	
	/**
	 * 获取JmsTopicTemplate
	 */
	public JmsTemplate getJmsTopicTemplate();
	
	/**
	 * 指定消息发送的目标（主题），发送object消息（包括对象/文本）
	 * 
	 * @param destinationName
	 *            消息目标（主题）
	 * @param objectMessage
	 *            object消息
	 * @param flag
	 *            队列发送（false）/广播发送（true）
	 */
	public void sendObjectMessage(String destinationName, Object objectMessage, boolean flag);

	/**
	 * 指定消息发送的目标（主题），发送object消息（包括对象/文本）
	 * 
	 * @param destinationName
	 *            消息目标（主题）
	 * @param objectMessage
	 *            object消息
	 * @param flag
	 *            队列发送（false）/订阅（广播）发送（true）
	 * @param messagePostProcessor
	 *            消息后置处理器，可以给objectMessage添加属性
	 */
	public void sendObjectMessageProcessor(String destinationName, Object objectMessage, boolean flag,
			MessagePostProcessor messagePostProcessor);
	
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
	public void sendObjectMessage(Destination destination, Object objectMessage, boolean flag);
	
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
	public void sendObjectMessageProcessor(Destination destination, Object objectMessage,boolean flag,MessagePostProcessor messagePostProcessor);
	
	/**
	 * 默认消息发送目标（主题），发送object消息（包括对象/文本）
	 * 
	 * @param objectMessage
	 *            object消息
	 * @param flag
	 *            队列发送（false）/订阅（广播）发送（true）
	 */
	public void sendObjectMessage(Object objectMessage, boolean flag);
	
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
	public void sendObjectMessageProcessor(Object objectMessage,boolean flag,MessagePostProcessor messagePostProcessor);
}
