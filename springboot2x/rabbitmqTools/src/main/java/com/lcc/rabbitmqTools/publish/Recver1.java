/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Recver1.java   
 * @Package com.lcc.rabbitmqTools.publish   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年2月19日 下午3:22:13   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcc.rabbitmqTools.publish;

import java.io.IOException;

import com.lcc.rabbitmqTools.util.ConnextionUtil;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

/**   
 * @ClassName:  Recver1   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年2月19日 下午3:22:13   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Recver1 {

	private final static String EXCHANGE_NAME = "testexchange";//队列名字

	public static void main(String[] args) throws Exception{
		Connection connection = ConnextionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("testpubqueue1",false,false,false,null);
		//绑定队列到交换机
		channel.queueBind("testpubqueue1", EXCHANGE_NAME, "");
		channel.basicQos(1);
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
		    public void handleDelivery(String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body )throws IOException
		    {
				System.out.println("消费者111111111:"+new String(body));
				this.getChannel().basicAck(envelope.getDeliveryTag(), false);
		    }
		};
		
		channel.basicConsume("testpubqueue1",false, consumer);
	}
}
