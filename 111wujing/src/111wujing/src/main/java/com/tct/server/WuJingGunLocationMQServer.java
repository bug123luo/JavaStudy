/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  GunLocationMQServer.java   
 * @Package com.tct.server   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月13日 下午5:30:00   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**   
 * @ClassName:  GunLocationMQServer   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月13日 下午5:30:00   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class WuJingGunLocationMQServer {

	/**   
	 * @Title: main   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param args      
	 * @return: void      
	 * @throws   
	 */
	public static void main(String[] args) {
		String[] xmlFiles= {
				"classpath:applicationContext-base.xml",
				"classpath:applicationContext-dao.xml",
				"classpath:applicationContext-transaction.xml",
				"classpath:applicationContext-jms.xml",
				//"classpath:applicationContext-timer.xml",
				"classpath:applicationContext-redis.xml"};
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlFiles);

	}

}
