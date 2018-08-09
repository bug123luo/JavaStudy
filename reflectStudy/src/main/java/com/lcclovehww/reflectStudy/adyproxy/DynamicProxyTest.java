/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  DynamicProxyTest.java   
 * @Package com.lcclovehww.reflectStudy   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月8日 上午9:20:48   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.reflectStudy.adyproxy;

import java.lang.reflect.Proxy;

import com.lcclovehww.reflectStudy.astatic.BuyHouseImpl;
import com.lcclovehww.reflectStudy.service.BuyHouse;

/**   
 * @ClassName:  DynamicProxyTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月8日 上午9:20:48   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class DynamicProxyTest {

	/**   
	 * @Title: main   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param args      
	 * @return: void      
	 * @throws   
	 */
	public static void main(String[] args) {
		BuyHouse buyHouse = new BuyHouseImpl();
		BuyHouse proxyBuyHouse = (BuyHouse) Proxy.newProxyInstance(buyHouse.getClass().getClassLoader(), new Class[] {BuyHouse.class}, new DynamicPoxyHandler(buyHouse));
		proxyBuyHouse.buyHouse();

	}

}
