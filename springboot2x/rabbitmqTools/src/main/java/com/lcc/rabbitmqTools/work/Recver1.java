/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Recver.java   
 * @Package com.lcc.rabbitmqTools.hello   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年2月19日 上午11:10:00   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcc.rabbitmqTools.work;

import java.io.IOException;
import com.lcc.rabbitmqTools.util.ConnextionUtil;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;

/**   
 * @ClassName:  Recver   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年2月19日 上午11:10:00   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Recver1 {

	private final static String QUEUE = "testwork";//队列名字
	
	public static void main(String[] args) throws Exception{
		Connection connection=ConnextionUtil.getConnection();
		final Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE, false, false, false, null);
		channel.basicQos(1);//告诉服务器，在我没有确认当前消息完成之前，不要给我发送消息
		
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
		    public void handleDelivery(String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body)
			throws IOException
			{
			 	System.out.println("消费者1收到的内容是: "+ new String(body));
			 	try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	channel.basicAck(envelope.getDeliveryTag(), false);//参数2，false为确认收到消息，true为拒接
			}
		};
		
		channel.basicConsume(QUEUE, false, consumer);		
	}
}
