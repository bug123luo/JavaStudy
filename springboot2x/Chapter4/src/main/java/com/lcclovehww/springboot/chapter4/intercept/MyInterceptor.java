/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MyInterceptor.java   
 * @Package com.lcclovehww.springboot.chapter4.intercept   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月11日 下午3:22:08   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter4.intercept;

import java.lang.reflect.InvocationTargetException;

import com.lcclovehww.springboot.chapter4.invoke.Invocation;

/**   
 * @ClassName:  MyInterceptor   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月11日 下午3:22:08   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class MyInterceptor implements Interceptor {

	/**   
	 * <p>Title: before</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.lcclovehww.springboot.chapter4.intercept.Interceptor#before()   
	 */
	@Override
	public boolean before() {
		System.out.println("before .......");
		return true;
	}

	/**   
	 * <p>Title: after</p>   
	 * <p>Description: </p>      
	 * @see com.lcclovehww.springboot.chapter4.intercept.Interceptor#after()   
	 */
	@Override
	public void after() {
		System.out.println("after ......");
	}

	/**   
	 * <p>Title: afterReturning</p>   
	 * <p>Description: </p>      
	 * @see com.lcclovehww.springboot.chapter4.intercept.Interceptor#afterReturning()   
	 */
	@Override
	public void afterReturning() {
		System.out.println("afterReturning ......");
	}

	/**   
	 * <p>Title: afterThrowing</p>   
	 * <p>Description: </p>      
	 * @see com.lcclovehww.springboot.chapter4.intercept.Interceptor#afterThrowing()   
	 */
	@Override
	public void afterThrowing() {
		System.out.println("afterThrowing ......");
	}

	/**   
	 * <p>Title: userAround</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.lcclovehww.springboot.chapter4.intercept.Interceptor#userAround()   
	 */
	@Override
	public boolean userAround() {
		return true;
	}

	/**   
	 * <p>Title: around</p>   
	 * <p>Description: </p>   
	 * @param invocation
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException   
	 * @see com.lcclovehww.springboot.chapter4.intercept.Interceptor#around(com.lcclovehww.springboot.chapter4.intercept.Invocation)   
	 */
	@Override
	public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
		System.out.println("around before ......");
		Object obj=invocation.proceed();
		System.out.println("around after ......");
		return obj;
	}

}
