/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserController.java   
 * @Package com.lcclovehww.springboot.chapter6.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月21日 上午10:54:49   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter6.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Strings.StringToAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcclovehww.springboot.chapter6.pojo.User;
import com.lcclovehww.springboot.chapter6.service.UserBatchService;
import com.lcclovehww.springboot.chapter6.service.UserService;

/**   
 * @ClassName:  UserController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月21日 上午10:54:49   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService =null;
	
	@Autowired
	private UserBatchService userBatchService = null;
	
	
	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser(Long id) {
		return userService.getUser(id);
	}
	
	
	@RequestMapping("/insertUser")
	@ResponseBody
	public Map<String, Object> insertUser(String userName, String note){
		User user= new User();
		user.setUserName(userName);
		user.setNote(note);
		
		int update = userService.insertUser(user);
		Map<String, Object> result = new HashMap<>();
		result.put("success", update==1);
		result.put("user", user);
		return result;
	}
	
	@RequestMapping("/insertUsers")
	@ResponseBody
	public Map<String, Object> insertUsers(String userName1, String note1, String userName2, String note2){
		User user1 = new User();
		user1.setUserName(userName1);
		user1.setNote(note1);
		User user2 = new User();
		user2.setUserName(userName2);
		user2.setNote(note2);
		
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		
		int inserts = userBatchService.insertUsers(userList);
		Map<String, Object> result = new HashMap<>();
		result.put("success", inserts>0);
		result.put("user", userList);
		return result;
		
	}
	
	@RequestMapping("/insertDirectorCallUsers")
	@ResponseBody
	public Map<String, Object> insertTestUsers(String userName1, String note1, String userName2, String note2){
		User user1 = new User();
		user1.setUserName(userName1);
		user1.setNote(note1);
		User user2 = new User();
		user2.setUserName(userName2);
		user2.setNote(note2);
		
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		
		int inserts = userService.insertUsers(userList);
		Map<String, Object> result = new HashMap<>();
		result.put("success", inserts>0);
		result.put("user", userList);
		return result;
	}
}
