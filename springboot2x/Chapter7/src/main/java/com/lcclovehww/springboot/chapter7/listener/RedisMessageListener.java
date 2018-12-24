/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  RedisMessageListener.java   
 * @Package com.lcclovehww.springboot.chapter7.listener   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月24日 下午4:07:15   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter7.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**   
 * @ClassName:  RedisMessageListener   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月24日 下午4:07:15   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Component
public class RedisMessageListener implements MessageListener {

	/**   
	 * <p>Title: onMessage</p>   
	 * <p>Description: </p>   
	 * @param message
	 * @param pattern   
	 * @see org.springframework.data.redis.connection.MessageListener#onMessage(org.springframework.data.redis.connection.Message, byte[])   
	 */
	@Override
	public void onMessage(Message message, byte[] pattern) {
		//消息体
		String body = new String(message.getBody());
		//渠道名称
		String topic = new String(pattern);
		System.out.println(body);
		System.out.println(topic);
	}

}
