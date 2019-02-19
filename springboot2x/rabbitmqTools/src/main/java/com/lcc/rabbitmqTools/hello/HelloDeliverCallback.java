/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  HelloDeliverCallback.java   
 * @Package com.lcc.rabbitmqTools.hello   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年2月19日 下午2:16:58   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcc.rabbitmqTools.hello;

import java.io.IOException;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

/**   
 * @ClassName:  HelloDeliverCallback   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年2月19日 下午2:16:58   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class HelloDeliverCallback implements DeliverCallback {

	/**   
	 * <p>Title: handle</p>   
	 * <p>Description: </p>   
	 * @param consumerTag
	 * @param message
	 * @throws IOException   
	 * @see com.rabbitmq.client.DeliverCallback#handle(java.lang.String, com.rabbitmq.client.Delivery)   
	 */
	public void handle(String consumerTag, Delivery message) throws IOException {
        String messageTemp = new String(message.getBody(), "UTF-8");
        System.out.println(" [x] Received '" + messageTemp + "'");
	}

}
