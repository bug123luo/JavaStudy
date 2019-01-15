/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  AsyncController.java   
 * @Package com.lcclovehww.springboot.chapter13.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月15日 下午2:12:30   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lcclovehww.springboot.chapter13.service.AsyncService;

/**   
 * @ClassName:  AsyncController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月15日 下午2:12:30   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Controller
@RequestMapping("/async")
public class AsyncController {

	@Autowired
	private AsyncService asyncService = null;
	
	@GetMapping("/page")
	public String asyncPage() {
		System.out.println("请求线程名称: "+"【"+Thread.currentThread().getName()+"】");
		asyncService.generateReport();
		return "async";
	}
}
