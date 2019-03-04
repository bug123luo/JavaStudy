/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Test.java   
 * @Package com.lcc.springboot.springbootjasypt.main   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年3月4日 上午11:34:46   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcc.springboot.springbootjasypt.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**   
 * @ClassName:  Test   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年3月4日 上午11:34:46   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
@RestController
public class DatabaseUsernameAndPasswordEncry {

	 @Value("${spring.datasource.username}")
	 private String username; 
	 @Value("${spring.datasource.password}") 
	 private String password; 
	 @RequestMapping("/getRealInfo") 
	 String getRealInfo() 
	 { return "username: " + username + ", " + "password: " + password; }

}
