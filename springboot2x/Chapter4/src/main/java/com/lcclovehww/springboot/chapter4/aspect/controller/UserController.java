/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserController.java   
 * @Package com.lcclovehww.springboot.chapter4.aspect.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月11日 下午8:04:21   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter4.aspect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcclovehww.springboot.chapter4.aspect.service.UserService;
import com.lcclovehww.springboot.chapter4.aspect.validator.UserValidator;
import com.lcclovehww.springboot.chapter4.jdbc.pojo.User;

import net.bytebuddy.asm.Advice.Return;

/**   
 * @ClassName:  UserController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月11日 下午8:04:21   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService=null;
	
	@RequestMapping("/print")
	@ResponseBody
	public User printUser(Long id, String userName, String note) {
		User user = new User();
		user.setId(id);
		user.setUsername(userName);
		user.setNote(note);
		
		userService.printUser(user);
		return user;
	}
	
	@RequestMapping("/vp")
	@ResponseBody
	public User validateAndPrint(Long id, String userName, String note) {
		User user = new User();
		user.setId(id);
		user.setUsername(userName);
		user.setNote(note);
		
		UserValidator userValidator = (UserValidator)userService;
		
		if(userValidator.validate(user)) {
			userService.printUser(user);
		}
		
		return user;
	}
}
