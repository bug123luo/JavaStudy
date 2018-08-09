/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  HelloServiceCgLib.java   
 * @Package com.lcclovehww.reflectStudy.mybatis.cglib.dyproxy   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月8日 上午9:58:14   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.reflectStudy.mybatis.cglib.dyproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**   
 * @ClassName:  HelloServiceCgLib   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月8日 上午9:58:14   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class HelloServiceCgLib implements MethodInterceptor {

	private Object target;
	
	public Object getInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	/**   
	 * <p>Title: intercept</p>   
	 * <p>Description: </p>   
	 * @param obj
	 * @param method
	 * @param args
	 * @param proxy
	 * @return
	 * @throws Throwable   
	 * @see net.sf.cglib.proxy.MethodInterceptor#intercept(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], net.sf.cglib.proxy.MethodProxy)   
	 */
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.err.println("#######################我是CGLIB的动态代理######################");
		System.err.println("我准备说hello");
		Object returnObj = proxy.invokeSuper(obj, args);
		System.err.println("我说过hello了");
		return returnObj;
	}

}
