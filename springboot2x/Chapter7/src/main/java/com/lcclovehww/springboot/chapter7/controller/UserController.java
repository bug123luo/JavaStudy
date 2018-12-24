/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserController.java   
 * @Package com.lcclovehww.springboot.chapter7.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月24日 下午5:39:43   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter7.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lcclovehww.springboot.chapter7.pojo.User;
import com.lcclovehww.springboot.chapter7.service.UserService;

/**   
 * @ClassName:  UserController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月24日 下午5:39:43   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService = null;
	
	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser(Long id) {
		return userService.getUser(id);
	}
	
	@RequestMapping("/insertUser")
	@ResponseBody
	public User insertUser(String userName, String note) {
		User user = new User();
		user.setNote(note);
		user.setUserName(userName);
		userService.insertUser(user);
		
		return user;
	}
	
	@RequestMapping("/findUsers")
	@ResponseBody
	public List<User> findUsers(String userName, String note){
		return userService.findUsers(userName, note);
	}
	
	
	@RequestMapping("/updateUserName")
	@ResponseBody
	public Map<String, Object> updateUserName(Long id, String userName){
		User user  = userService.updateUserName(id, userName);
		boolean flag = user!=null;
		String message = flag?"更新成功":"更新失败";
		return resultMap(flag,message);
	} 
	
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Map<String, Object> deleteUser(Long id){
		int result = userService.deleteUser(id);
		boolean flag = result ==1;
		String message = flag?"删除成功":"删除失败";
		return resultMap(flag, message);
	}
	
	private Map<String, Object> resultMap(boolean success, String message) {
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("success", success);
		result.put("message", message);
		return result;
	}
}
