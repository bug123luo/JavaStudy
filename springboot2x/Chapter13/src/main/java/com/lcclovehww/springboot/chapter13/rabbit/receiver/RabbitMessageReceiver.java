/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  RabbitMessageReceiver.java   
 * @Package com.lcclovehww.springboot.chapter13.rabbit.receiver   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月16日 下午5:07:09   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.rabbit.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lcclovehww.springboot.chapter13.mapper.PersonLocationMapper;
import com.lcclovehww.springboot.chapter13.pojo.IotJsonMsg;
import com.lcclovehww.springboot.chapter13.pojo.MsgBody;
import com.lcclovehww.springboot.chapter13.pojo.PersonLocation;
import com.lcclovehww.springboot.chapter13.pojo.User;
import com.lcclovehww.springboot.chapter13.util.IotStringToClass;
import com.sun.xml.internal.stream.Entity;

import sun.net.www.content.text.plain;

/**   
 * @ClassName:  RabbitMessageReceiver   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月16日 下午5:07:09   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Component
public class RabbitMessageReceiver {
	
	@Autowired
	PersonLocationMapper personLocationMapper;
	
	@RabbitListener(queues= {"${rabbitmq.queue.msg}"})
	public void receiveMsg(String msg) {
		System.out.println("收到消息: 【"+msg+"】");
		
		MsgBody msgBody=IotStringToClass.changeToIotMsg(msg);
		
		PersonLocation personLocation = new PersonLocation();
		
		personLocation.setBaseStationId(msgBody.getBase());
		personLocation.setDeviceId(msgBody.getRepeater());
		personLocation.setStatus(msgBody.getStatus());
		
		personLocationMapper.insert(personLocation);
		
		//System.out.println(iotJsonMsg.toString());

	}
	
	@RabbitListener(queues= {"${rabbitmq.queue.user}"})
	public void receiveUser(User user) {
		System.out.println("收到用户消息： 【"+user+"】");
	}
	
}
