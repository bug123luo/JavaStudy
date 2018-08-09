/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  HelloServiceProxy.java   
 * @Package com.lcclovehww.reflectStudy   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月8日 上午9:36:28   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.reflectStudy.mybatis.jdk.dyproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.omg.CORBA.OBJECT_NOT_EXIST;

/**   
 * @ClassName:  HelloServiceProxy   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月8日 上午9:36:28   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class HelloServiceProxy implements InvocationHandler {

	private Object target;
	
	public Object bind(Object target) {
		this.target = target;
		
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
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
		System.err.println("#############我是jdk动态代理##############");
		Object result = null;
		
		System.err.println("我准备说hello.");
		
		result = method.invoke(target, args);
		
		System.err.println("我说过hello 了");
		
		return result;
	}

}
