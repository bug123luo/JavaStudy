/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ActiveMqServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter13.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月15日 下午3:23:06   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import com.lcclovehww.springboot.chapter13.service.ActiveMqService;

/**   
 * @ClassName:  ActiveMqServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月15日 下午3:23:06   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class ActiveMqServiceImpl implements ActiveMqService{

	@Autowired
	private JmsTemplate jmsTemplate = null;
	
	@Override
	public void sendMsg(String message) {
		System.out.println("发送消息【"+message+"】");
		jmsTemplate.convertAndSend(message);
		//user defined
		//jmsTemplate.convertAndSend("your-destination",message);
	}

	/**   
	 * <p>Title: receiveMsg</p>   
	 * <p>Description: </p>   
	 * @param message   
	 * @see com.lcclovehww.springboot.chapter13.service.ActiveMqService#receiveMsg(java.lang.String)   
	 */
	@Override
	@JmsListener(destination="${spring.jms.template.default-destination}")
	public void receiveMsg(String message) {
		System.out.println("接收到消息：【"+message+"】");
	}
	
}
