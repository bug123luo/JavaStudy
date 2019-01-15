/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ActiveMqService.java   
 * @Package com.lcclovehww.springboot.chapter13.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月15日 下午3:21:40   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.service;

/**   
 * @ClassName:  ActiveMqService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月15日 下午3:21:40   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface ActiveMqService {
	
	public void sendMsg(String message);
	
	public void receiveMsg(String message);
}
