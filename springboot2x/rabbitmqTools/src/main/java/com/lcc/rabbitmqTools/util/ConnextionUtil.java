/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ConnextionUtil.java   
 * @Package com.lcc.rabbitmqTools   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年2月19日 上午10:52:15   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcc.rabbitmqTools.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**   
 * @ClassName:  ConnextionUtil   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年2月19日 上午10:52:15   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class ConnextionUtil {

	public static Connection getConnection() throws Exception{
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("112.74.51.194");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("test");
		connectionFactory.setPassword("860413");
		connectionFactory.setVirtualHost("/test");
		return connectionFactory.newConnection();//创建一个新的连接
	}
}
