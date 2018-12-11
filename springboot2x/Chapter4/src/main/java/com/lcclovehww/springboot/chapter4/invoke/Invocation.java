/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Invocation.java   
 * @Package com.lcclovehww.springboot.chapter4.invoke   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月11日 下午3:15:46   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter4.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**   
 * @ClassName:  Invocation   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月11日 下午3:15:46   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */


public class Invocation {
	@Getter
	@Setter
	private Object[] params;
	@Getter
	@Setter
	private Method method;
	@Getter
	@Setter
	private Object target;
	
	public Invocation(Object target, Method method, Object[] params) {
		this.target = target;
		this.method = method;
		this.params = params;
	}
	
	public Object proceed() throws InvocationTargetException, IllegalAccessException{
		return method.invoke(target, params);
	}
}
