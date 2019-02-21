/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Sender.java   
 * @Package com.lcc.rabbitmqTools.persist   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年2月19日 下午4:38:53   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcc.rabbitmqTools.persist;

import com.lcc.rabbitmqTools.util.ConnextionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**   
 * @ClassName:  Sender   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年2月19日 下午4:38:53   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Sender {

	private static String EXCHANGE_NAME="testpersist1";
	
	public static void main(String[] args) throws Exception{
		Connection connection = ConnextionUtil.getConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
		channel.basicPublish(EXCHANGE_NAME, "abc", MessageProperties.PERSISTENT_TEXT_PLAIN, "持久化消息".getBytes());
		channel.close();
		connection.close();
	}
}
