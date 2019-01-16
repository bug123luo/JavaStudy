/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ActiveMqController.java   
 * @Package com.lcclovehww.springboot.chapter13.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月15日 下午3:56:10   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lcclovehww.springboot.chapter13.pojo.User;
import com.lcclovehww.springboot.chapter13.service.ActiveMqService;
import com.lcclovehww.springboot.chapter13.service.ActiveMqUserService;

/**   
 * @ClassName:  ActiveMqController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月15日 下午3:56:10   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */


@Controller
@RequestMapping("/activemq")
public class ActiveMqController {
	
	@Autowired
	private ActiveMqService activeMqService = null;
	
	@Autowired
	private ActiveMqUserService activeMqUserService = null;
	
	@ResponseBody
	@GetMapping("/msg")
	public Map<String, Object> msg(String message){
		activeMqService.sendMsg(message);
		return result(true,message);
	}
	
	@ResponseBody
	@GetMapping("/user")
	public Map<String, Object> sendUser(Long id, String userName, String note){
		User user = new User(id, userName, note);
		activeMqUserService.sendUser(user);
		return result(true, user);
	}
	
	private Map<String, Object> result(Boolean success, Object message){
		Map<String, Object> result = new HashMap<>();
		result.put("success", success);
		result.put("message", message);
		return result;
	}
}
