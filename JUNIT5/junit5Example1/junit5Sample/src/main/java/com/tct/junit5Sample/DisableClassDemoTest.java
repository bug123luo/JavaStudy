/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  DisableClassDemoTest.java   
 * @Package com.tct.junit5Sample   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月14日 上午10:05:43   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.junit5Sample;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**   
 * @ClassName:  DisableClassDemoTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月14日 上午10:05:43   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
class DisableClassDemoTest {

	/**
	 * Test method for {@link com.tct.junit5Sample.DisableClassDemo#testWillBeSkipped()}.
	 */
	@Test
	@Disabled
	void testTestWillBeSkipped() {
		fail("Not yet implemented");
	}
	
	@Test
	void testWillBeExecuted() {
		
	}

}
