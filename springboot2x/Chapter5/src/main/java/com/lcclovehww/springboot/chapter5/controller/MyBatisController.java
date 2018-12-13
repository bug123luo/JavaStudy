/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MyBatisController.java   
 * @Package com.lcclovehww.springboot.chapter5.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月13日 下午3:10:50   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcclovehww.springboot.chapter5.pojo.MybatisUser;
import com.lcclovehww.springboot.chapter5.service.MyBatisUserService;

/**   
 * @ClassName:  MyBatisController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月13日 下午3:10:50   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Controller
@RequestMapping("/mybatis")
public class MyBatisController {

	@Autowired
	private MyBatisUserService myBatisUserService = null;
	
	@RequestMapping("/getUser")
	@ResponseBody
	public MybatisUser getUser(Long id) {
		MybatisUser user = myBatisUserService.getUser(id);
		return user;
	}
}
