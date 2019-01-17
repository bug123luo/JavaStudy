/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  WebSocketController.java   
 * @Package com.lcclovehww.springboot.chapter13.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月17日 上午11:19:55   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**   
 * @ClassName:  WebSocketController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月17日 上午11:19:55   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

	@GetMapping("/index")
	public String websocket() {
		return "websocket";
	}
}
