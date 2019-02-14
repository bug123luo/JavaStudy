/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserController.java   
 * @Package com.lcclovehww.springboot.chapter12.main   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月18日 下午4:07:16   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter12.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcclovehww.springboot.chapter12.pojo.DatabaseUser;
import com.lcclovehww.springboot.chapter12.service.UserRoleService;

/**   
 * @ClassName:  UserController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月18日 下午4:07:16   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Controller
public class WelComeController {
	
	@Autowired
	private UserRoleService userRoleService = null;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	@GetMapping("/logout_welcome")
	public String logtoutwelcome() {
		return "logout_welcome";
	}
	
	@GetMapping("/user/details")
	@ResponseBody
	public DatabaseUser getUser(String userName) {
		return userRoleService.getUserByName(userName);
	}
	
	@GetMapping("/user/welcome")
	public String userWelcome() {
		return "welcome";
	}
	
	@GetMapping("/admin/welcome")
	public String adminWelcome() {
		return "welcome";
	}
	
	@GetMapping("/admin/welcome1")
	public String adminWelcome1() {
		return "welcome";
	}
	
	@GetMapping("/admin/welcome2")
	public String adminWelcome2() {
		return "welcome";
	}
	
	@GetMapping("/csrf/form")
	public String csrfPage() {
		return "csrf_form";
	}
	
	@PostMapping("/csrf/commit")
	@ResponseBody
	public Map<String, String> csrfCommit(String name, String describe) {
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("describe", describe);
		return map;
	}
}
