/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Interceptor.java   
 * @Package com.lcclovehww.springboot.chapter4.intercept   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月11日 下午3:11:18   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter4.intercept;

import java.lang.reflect.InvocationTargetException;

import com.lcclovehww.springboot.chapter4.invoke.Invocation;

/**   
 * @ClassName:  Interceptor   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月11日 下午3:11:18   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface Interceptor {

	public boolean before();
	
	public void after();
	
	public Object around(Invocation invocation) throws InvocationTargetException,IllegalAccessException;
	
	public void afterReturning();
	
	public void afterThrowing();
	
	boolean userAround();
}
