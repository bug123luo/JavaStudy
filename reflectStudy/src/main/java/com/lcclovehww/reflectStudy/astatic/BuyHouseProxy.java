/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  BuyHouseProxy.java   
 * @Package com.lcclovehww.reflectStudy   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月8日 上午9:12:21   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.reflectStudy.astatic;

import com.lcclovehww.reflectStudy.service.BuyHouse;

/**   
 * @ClassName:  BuyHouseProxy   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月8日 上午9:12:21   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class BuyHouseProxy implements BuyHouse {

	private BuyHouse buyHouse;
	
	public BuyHouseProxy(final BuyHouse buyHouse) {
		this.buyHouse = buyHouse;
	}
	
	/**   
	 * <p>Title: buyHouse</p>   
	 * <p>Description: </p>      
	 * @see com.lcclovehww.reflectStudy.service.BuyHouse#buyHouse()   
	 */
	public void buyHouse() {
		System.out.println("买房前准备");
		buyHouse.buyHouse();
		System.out.println("买房后装修");

	}

}
