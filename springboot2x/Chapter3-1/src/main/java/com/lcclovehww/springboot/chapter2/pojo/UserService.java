/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserService.java   
 * @Package com.lcclovehww.springboot.chapter2.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月6日 下午8:14:58   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter2.pojo;

import org.springframework.stereotype.Service;

/**   
 * @ClassName:  UserService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月6日 下午8:14:58   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class UserService {
	public void printUser(User user) {
		System.out.println("编号:"+user.getId());
		System.out.println("用户民称:"+user.getUserName());
		System.out.println("备注:"+user.getNote());
	}
}
