package com.mq.consumer;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.mq.pojo.User;

@Component
public class ConsumerTopic2 implements MessageListener {

	public void onMessage(Message message) {
		
		// 获得MapMessage的键值对消息
		// MapMessage mapMessage = (MapMessage) message;
		// String str1 = "";
		// String str2 = "";
		// try {
		// str1 = mapMessage.getString("topic1Key");
		// str2 = mapMessage.getString("topic2Key");
		// } catch (JMSException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// System.out.println("ConsumerTopic2:" + str1);
		// System.out.println("ConsumerTopic2:" + str2);

		// 接收普通的文本消息
		 TextMessage textMessage = (TextMessage) message;
		 try {
		 String text = textMessage.getText();
		 System.out.println("ConsumerTopic2 接收到：" + text);
		 } catch (JMSException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }

		// 接收Pojo消息
//		ObjectMessage objectMessage = (ObjectMessage) message;
//		User user = null;
//		float floatProperty = 0.0F;
//		try {
//			user = (User) objectMessage.getObject();
//			floatProperty = objectMessage.getFloatProperty("floalt");
//		} catch (JMSException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("ConsumerTopic2：" + user);
//		System.out.println("ConsumerTopic2属性" + floatProperty);
	}
}