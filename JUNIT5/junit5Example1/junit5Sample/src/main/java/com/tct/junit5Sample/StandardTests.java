/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  StandardTests.java   
 * @Package com.tct.junit5Sample   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月14日 上午8:50:57   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.junit5Sample;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**   
 * @ClassName:  StandardTests   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月14日 上午8:50:57   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

//不必将测试类和测试方法声明为public
public class StandardTests {

	@BeforeAll
	static void initAll() {
		
	}
	
	@BeforeEach
	void init() {
		
	}
	
	@Test
	void succeedingTest() {
		
	}
	
	@Test
	void failingTest() {
		fail("a failing	test");
	}
	
	@Test
	@Disabled("for demonstration purposes")
	void skippedTest() {
		
	}
	
	@AfterEach
	void tearDown() {
		
	}
	
	@AfterAll
	static void tearDownAll() {
		
	}
}
