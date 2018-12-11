/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  HelloServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter4.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月11日 下午3:08:35   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter4.service.impl;

import com.lcclovehww.springboot.chapter4.service.HelloService;

/**   
 * @ClassName:  HelloServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月11日 下午3:08:35   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class HelloServiceImpl implements HelloService {

	/**   
	 * <p>Title: sayHello</p>   
	 * <p>Description: </p>   
	 * @param name   
	 * @see com.lcclovehww.springboot.chapter4.service.HelloService#sayHello(java.lang.String)   
	 */
	@Override
	public void sayHello(String name) {
		if(name == null || name.trim() =="") {
			throw new RuntimeException("parameter is null!!");
		}

		System.out.println("hello"+name);
	}

}
