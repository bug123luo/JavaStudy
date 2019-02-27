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
package com.lcclovehww.springboot.chapter11.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lcclovehww.springboot.chapter11.enumeration.SexEnum;
import com.lcclovehww.springboot.chapter11.pojo.User;
import com.lcclovehww.springboot.chapter11.service.UserService;
import com.lcclovehww.springboot.chapter11.vo.UserVo;

import lombok.Data;

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
public class UserController {
	
	@Autowired
	private UserService userService = null;
	
	@RequestMapping("/user/restful")
	public String index() {
		return "restful";
	}
	
	private User changeToPo(UserVo userVo) {
		
		User user = new User();
		user.setId(userVo.getId());
		user.setUserName(userVo.getUserName());
		user.setSex(SexEnum.getEnumById(userVo.getSexCode()));
		user.setNote(userVo.getNote());
		
		return user;
	}
	
	private UserVo changeToVo(User user) {
		
		UserVo userVo= new UserVo();
		userVo.setId(user.getId());
		userVo.setUserName(user.getUserName());
		userVo.setSexCode(user.getSex().getCode());
		userVo.setSexName(user.getSex().getName());
		userVo.setNote(user.getNote());
		
		return userVo;
	}
	
	private List<UserVo> changeToVoes(List<User> poList){
		
		List<UserVo> voList = new ArrayList<>();
		
		for(User user:poList) {
			UserVo userVo =  changeToVo(user);
			voList.add(userVo);
		}
		return voList;
		
	}
	
	@Data
	public class ResultVo{
		private Boolean success = null;
		private String message = null;
	}
}
