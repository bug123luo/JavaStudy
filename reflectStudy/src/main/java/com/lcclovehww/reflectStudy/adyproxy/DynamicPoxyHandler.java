/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  DynamicPoxyHandler.java   
 * @Package com.lcclovehww.reflectStudy   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月8日 上午9:17:31   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.reflectStudy.adyproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**   
 * @ClassName:  DynamicPoxyHandler   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月8日 上午9:17:31   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class DynamicPoxyHandler implements InvocationHandler {

	private Object object;
	
	public DynamicPoxyHandler(final Object object) {
		this.object =  object;
	}
	
	/**   
	 * <p>Title: invoke</p>   
	 * <p>Description: </p>   
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable   
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])   
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("买房前准备");
		Object result = method.invoke(object, args);
		System.out.println("买房后装修");
		return result;
	}

}
