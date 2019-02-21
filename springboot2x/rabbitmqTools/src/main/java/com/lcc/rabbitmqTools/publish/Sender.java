/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Sender.java   
 * @Package com.lcc.rabbitmqTools.hello   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年2月19日 上午10:57:56   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcc.rabbitmqTools.publish;

import com.lcc.rabbitmqTools.util.ConnextionUtil;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**   
 * @ClassName:  Sender   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年2月19日 上午10:57:56   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Sender {

		private final static String EXCHANGE_NAME = "testexchange";//队列名字
		
		public static void main(String[] args) throws Exception{
			//获取连接
			Connection connection = ConnextionUtil.getConnection();
			//创建通道 
			Channel channel = connection.createChannel();
			//声明交换机
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//定义一个交换机，类型是fanout,也就是发布订阅模式
			//发布订阅模式的，因为消息是先发到交换机中，而交换机是没有保存功能的，所以如果没有消费者，消息会丢失
			channel.basicPublish(EXCHANGE_NAME, "", null, "发布订阅模式的消息".getBytes());
			channel.close();
			connection.close();
		}
}
