/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  RabbitMqServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter13.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月16日 下午4:51:22   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.service.impl;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lcclovehww.springboot.chapter13.pojo.User;
import com.lcclovehww.springboot.chapter13.service.RabbitMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;

/**   
 * @ClassName:  RabbitMqServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月16日 下午4:51:22   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class RabbitMqServiceImpl implements RabbitMqService, ConfirmCallback {

	@Value("${rabbitmq.queue.msg}")
	private String msgRouting =null;
	
	@Value("${rabbitmq.queue.user}")
	private String userRouting =null;
	
	@Autowired
	private RabbitTemplate rabbitTemplate =null;
	
	/**   
	 * <p>Title: sendMsg</p>   
	 * <p>Description: </p>   
	 * @param msg   
	 * @see com.lcclovehww.springboot.chapter13.service.RabbitMqService#sendMsg(java.lang.String)   
	 */
	@Override
	public void sendMsg(String msg) {
		System.out.println("发送消息: 【"+msg+"】");
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.convertAndSend(msgRouting, msg);
	}

	/**   
	 * <p>Title: sendUser</p>   
	 * <p>Description: </p>   
	 * @param user   
	 * @see com.lcclovehww.springboot.chapter13.service.RabbitMqService#sendUser(com.lcclovehww.springboot.chapter13.pojo.User)   
	 */
	@Override
	public void sendUser(User user) {
		System.out.println("发送用户消息: 【"+user+"】");
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.convertAndSend(userRouting, user);
	}

	/**   
	 * <p>Title: confirm</p>   
	 * <p>Description: </p>   
	 * @param correlationData
	 * @param ack
	 * @param cause   
	 * @see org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback#confirm(org.springframework.amqp.rabbit.connection.CorrelationData, boolean, java.lang.String)   
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if(ack) {
			System.out.println("消息成功消费");
		}else {
			System.out.println("消息消费失败："+cause);
		}
	}

}
