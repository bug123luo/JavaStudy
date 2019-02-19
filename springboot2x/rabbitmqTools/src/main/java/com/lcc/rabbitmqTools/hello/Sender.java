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
package com.lcc.rabbitmqTools.hello;

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

		private final static String QUEUE = "testhello";//队列名字
		
		public static void main(String[] args) throws Exception{
			//获取连接
			Connection connection = ConnextionUtil.getConnection();
			//创建通道 
			Channel channel = connection.createChannel();
			//声明队列,如果队列存在，则什么都不做，如果不存在才创建
			//参数一 队列的名字
			//参数二是否持久化队列，我们的队列模式是在内存中的，如果rabbitmq 重启会丢失，如果我们设置为true,则会保存到erlang自带的数据库中，重启后会重新读取 
			//参数三是否排外，有两个作用，第一个当我们的链接关闭后是否会自动删除队列，作用二 是否私有当前队列，如果私有了，其他通道不可以访问当前队列，如果为true ,一般是一个队列只是用一个消费者的时候
			//参数四是否自动删除
			//参数五 我们的一些其他参数
			channel.queueDeclare(QUEUE, false, false, false,null);
			//发送内容
			channel.basicPublish("", QUEUE, null, "发送的消息".getBytes());
			//关闭连接
			channel.close();
			connection.close();
		}
}
