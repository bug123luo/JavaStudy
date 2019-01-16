/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ActiveMqUserServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter13.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月15日 下午3:47:18   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.lcclovehww.springboot.chapter13.pojo.User;
import com.lcclovehww.springboot.chapter13.service.ActiveMqUserService;

/**   
 * @ClassName:  ActiveMqUserServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月15日 下午3:47:18   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class ActiveMqUserServiceImpl implements ActiveMqUserService {

	@Autowired
	private JmsTemplate jmsTemplate = null;
	
	private static final String myDestination = "my-destination";
	/**   
	 * <p>Title: sendUser</p>   
	 * <p>Description: </p>   
	 * @param user   
	 * @see com.lcclovehww.springboot.chapter13.service.ActiveMqUserService#sendUser(com.lcclovehww.springboot.chapter13.pojo.User)   
	 */
	@Override
	public void sendUser(User user) {
		System.out.println("发送消息【"+user+"】");
		jmsTemplate.convertAndSend(myDestination, user);
	}

	/**   
	 * <p>Title: receiveUser</p>   
	 * <p>Description: </p>   
	 * @param user   
	 * @see com.lcclovehww.springboot.chapter13.service.ActiveMqUserService#receiveUser(com.lcclovehww.springboot.chapter13.pojo.User)   
	 */
	@Override
	@JmsListener(destination=myDestination)
	public void receiveUser(User user) {
		System.out.println("接收到消息: 【"+user+"】");
	}

}
