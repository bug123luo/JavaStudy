/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ProxyBean.java   
 * @Package com.lcclovehww.springboot.chapter4.proxy   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月11日 下午3:45:00   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter4.proxy;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.hamcrest.CoreMatchers.nullValue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.lcclovehww.springboot.chapter4.intercept.Interceptor;
import com.lcclovehww.springboot.chapter4.invoke.Invocation;

import net.bytebuddy.asm.Advice.This;

/**   
 * @ClassName:  ProxyBean   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月11日 下午3:45:00   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class ProxyBean implements InvocationHandler {

	private Object target = null;

	private Interceptor interceptor = null;
	
	public static Object getProxyBean(Object target, Interceptor interceptor) {
		ProxyBean proxyBean = new ProxyBean();
		
		proxyBean.target = target;
		
		proxyBean.interceptor = interceptor;
		
		Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyBean);
		
		return proxy;
	}
	
	
	/**   
	 * <p>Title: invoke</p>   
	 * <p>Description: </p>   
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @throws Throwable   
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])   
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		boolean exceptionFlag = false;
		Invocation invocation = new Invocation(target, method, args);
		
		Object retObj = null;
		try {
			if(this.interceptor.before()) {
				retObj = this.interceptor.around(invocation);
			}else {
				retObj = method.invoke(target, args);
			}
		} catch (Exception e) {
			exceptionFlag = true;
		}
		this.interceptor.after();
		
		if(exceptionFlag) {
			this.interceptor.afterThrowing();
		}else {
			this.interceptor.afterReturning();
			return retObj;
		}
		return null;
	}

}
