/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  DisplayNameDemo.java   
 * @Package com.tct.junit5Sample   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月14日 上午9:03:23   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.junit5Sample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**   
 * @ClassName:  DisplayNameDemo   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月14日 上午9:03:23   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@DisplayName("A special test case")
public class DisplayNameDemo {

	@Test
	@DisplayName("Custom test name containing spaces")
	void testWithDisplayNameContainingSpaces() {	
	}
	
	@Test
	@DisplayName("-_-|||")
	void testWithDisplayNameContainingSpecialCharacters() {}
	
	@Test
	@DisplayName(":-(")
	void testWithDisplayNameContainingEmoji() {}
}
