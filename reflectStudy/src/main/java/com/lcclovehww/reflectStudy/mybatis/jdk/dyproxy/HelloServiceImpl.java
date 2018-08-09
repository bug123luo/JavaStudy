/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  HelloServiceImpl.java   
 * @Package com.lcclovehww.reflectStudy   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月8日 上午9:35:24   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.reflectStudy.mybatis.jdk.dyproxy;

/**   
 * @ClassName:  HelloServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月8日 上午9:35:24   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class HelloServiceImpl implements HelloService {

	/**   
	 * <p>Title: sayHello</p>   
	 * <p>Description: </p>   
	 * @param name   
	 * @see com.lcclovehww.reflectStudy.mybatis.jdk.dyproxy.HelloService#sayHello(java.lang.String)   
	 */
	public void sayHello(String name) {
		System.err.println("hello"+name);

	}

}
