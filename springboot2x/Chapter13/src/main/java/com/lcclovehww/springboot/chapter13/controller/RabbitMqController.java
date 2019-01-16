/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  RabbitMqController.java   
 * @Package com.lcclovehww.springboot.chapter13.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月16日 下午5:11:28   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lcclovehww.springboot.chapter13.pojo.User;
import com.lcclovehww.springboot.chapter13.service.RabbitMqService;

/**   
 * @ClassName:  RabbitMqController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月16日 下午5:11:28   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {

	@Autowired
	private RabbitMqService rabbitMqService = null;
	
	@GetMapping("/msg")
	public Map<String, Object> msg(String message){
		rabbitMqService.sendMsg(message);
		return resultMap("message",message);
	}
	
	@GetMapping("/user")
	public Map<String, Object> user(Long id,String userName, String note){
		User user=new User(id, userName, note);
		rabbitMqService.sendUser(user);
		return resultMap("user", user);
	}
	
	private Map<String, Object> resultMap(String key, Object obj){
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("message", obj);
		return result;
	}
}
