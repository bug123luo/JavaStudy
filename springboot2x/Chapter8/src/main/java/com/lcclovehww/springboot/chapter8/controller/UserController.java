/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserController.java   
 * @Package com.lcclovehww.springboot.chapter8.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月2日 下午3:06:15   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter8.controller;

import java.util.List;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcclovehww.springboot.chapter8.pojo.Role;
import com.lcclovehww.springboot.chapter8.pojo.User;
import com.lcclovehww.springboot.chapter8.repository.UserRepository;
import com.lcclovehww.springboot.chapter8.service.UserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**   
 * @ClassName:  UserController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月2日 下午3:06:15   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService = null;
	
	@Autowired
	private UserRepository userRepository = null;
	
	@RequestMapping("/byIdOrName")
	@ResponseBody
	public User findByIdOrUserName(@RequestParam(required=false) Long id, @RequestParam(required=false)String userName) {
		return userRepository.findUserByIdOrUserName(id, userName);
	}
	
	@RequestMapping("/byName")
	@ResponseBody
	public List<User> findByUserName(String userName){
		return userRepository.findByUserNameLike(userName);
	}
	
	/**   
	 * @Title: test   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: User      
	 * @throws   
	 */
	@RequestMapping("/HelloWorld")
	@ResponseBody
	public Role helloWorld() {
		Role role = new Role();
		role.setId(1l);
		role.setNote("sdsdfs");
		role.setRoleName("33333333");
		return role;
	}
	
	@RequestMapping("/page")
	public String page() {
		return "user";
	}

	@RequestMapping("/save")
	@ResponseBody
	public User saveUser(@RequestBody User user) {
		userService.saveUser(user);
		return user;
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public User getUser(Long id) {
		User user= userService.getUser(id);
		return user;
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public List<User> findUser(String userName, String note, Integer skip, Integer limit){
		List<User> userList = userService.findUser(userName, note, skip, limit);
		return userList;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public UpdateResult updateUser(Long id, String userName, String note) {
		return userService.updateUser(id, userName, note);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public DeleteResult deleteUser(Long id) {
		return userService.deleteUser(id);
	}
}
