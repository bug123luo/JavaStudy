package com.mq.consumer;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.mq.pojo.User;

@Component
public class ConsumerQueue1 implements MessageListener {

	public void onMessage(Message message) {

		//接收普通的文本消息
		 TextMessage textMessage = (TextMessage) message;
		 try {
		 String text = textMessage.getText();
//		 int i= 10/0;
		 System.out.println("ConsumerQueue1 接收到：" + text);
		 } catch (JMSException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		
		// 获得MapMessage的键值对消息
		// MapMessage mapMessage = (MapMessage) message;
		// String str1 = "";
		// String str2 = "";
		// try {
		// str1 = mapMessage.getString("queue1Key");
		// str2 = mapMessage.getString("queue2Key");
		// } catch (JMSException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// System.out.println("ConsumerQueue1:" + str1);
		// System.out.println("ConsumerQueue1:" + str2);

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
//		System.out.println("ConsumerQueue1：" + user);
//		System.out.println("ConsumerQueue1属性" + floatProperty);
	}

}
