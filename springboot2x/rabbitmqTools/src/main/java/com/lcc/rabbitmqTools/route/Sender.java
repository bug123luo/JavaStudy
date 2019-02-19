/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Sender.java   
 * @Package com.lcc.rabbitmqTools.route   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年2月19日 下午3:58:29   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcc.rabbitmqTools.route;

import com.lcc.rabbitmqTools.util.ConnextionUtil;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**   
 * @ClassName:  Sender   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年2月19日 下午3:58:29   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Sender {

	private final static String EXCHANGE_NAME = "testroute";
	
	public static void main(String[] args) throws Exception{
		Connection connection = ConnextionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		channel.basicPublish(EXCHANGE_NAME, "key3", null, "路由消息".getBytes());
		channel.close();
		connection.close();	
	}
}
