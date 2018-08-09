/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  CglibProxyTest.java   
 * @Package com.lcclovehww.reflectStudy.mybatis.cglib.dyproxy   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月8日 上午10:06:58   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.reflectStudy.mybatis.cglib.dyproxy;

import com.lcclovehww.reflectStudy.mybatis.jdk.dyproxy.HelloService;
import com.lcclovehww.reflectStudy.mybatis.jdk.dyproxy.HelloServiceImpl;

/**   
 * @ClassName:  CglibProxyTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月8日 上午10:06:58   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class MybatisCglibProxy {
	public static void main(String[] args) {
		HelloService helloService = new HelloServiceImpl();
		HelloServiceCgLib cglibProxy = new HelloServiceCgLib();
		HelloServiceImpl helloServiceCglibProxy = (HelloServiceImpl)cglibProxy.getInstance(helloService);
		helloServiceCglibProxy.sayHello("tangren");
	}
}
