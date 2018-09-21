package com.mq.provider;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mq.pojo.User;
import com.roger.activemq.IActiveMQAdapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-mqp.xml")
public class ProviderTest {

	@Autowired
	private IActiveMQAdapter activeMQAdapter;

	/**
	 * 队列模式 发送ObjectMessage，发送的信息为pojo时 需要实现Serializable接口
	 */
	@Test
	public void sendQueueObjectMessage() {
		User user = new User();
		user.setName("zhangshan");
		user.setAge("18");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("queue1Key", "queue1Value");
		hashMap.put("queue2Key", "queue2Value");
		// 普通的文本消息
		activeMQAdapter.sendObjectMessage("spring_queue", "队列文本消息", false);

		// 信息为pojo时，pojo需要实现Serializable接口
		// activeMQAdapter.sendObjectMessage("spring_queue", user, false);

		// map集合，键值对形式的消息，注：map的值不能为pojo
		// activeMQAdapter.sendObjectMessage("spring_queue", hashMap, false);

		// 发送ObjectMessage 加后置处理器 可以添加属性
//		activeMQAdapter.sendObjectMessageProcessor("spring_queue", user, false, new MessagePostProcessor() {
//			@Override
//			public Message postProcessMessage(Message message) throws JMSException {
//				// TODO 配饰消息的属性
//				message.setFloatProperty("floalt", 1.2F);
//				return message;
//			}
//		});
	}

	/**
	 * 广播模式 发送object消息 发送的信息为pojo时 需要实现Serializable接口
	 */
	@Test
	public void sendTopicObjectMessage() {
		User user = new User();
		user.setName("zhangshan");
		user.setAge("18");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("topic1Key", "topic1Value");
		hashMap.put("topic2Key", "topic2Value");
		// 普通的文本消息
		//activeMQAdapter.sendObjectMessage("spring_topic", "广播文本消息",true);
		activeMQAdapter.sendObjectMessage("TestTopic", "广播文本消息",true);
		// 信息为pojo时，pojo需要实现Serializable接口
		// activeMQAdapter.sendObjectMessage("spring_topic", user, true);
		
		// map集合，键值对形式的消息，注：map的值不能为pojo
		// activeMQAdapter.sendObjectMessage("spring_topic", hashMap, true);
		
		// 发送ObjectMessage 加后置处理器 可以添加属性
//		activeMQAdapter.sendObjectMessageProcessor("spring_topic", user, true, new MessagePostProcessor() {
//			@Override
//			public Message postProcessMessage(Message message) throws JMSException {
//				// TODO 配饰消息的属性
//				message.setFloatProperty("floalt", 1.2F);
//				return message;
//			}
//		});
	}
}
