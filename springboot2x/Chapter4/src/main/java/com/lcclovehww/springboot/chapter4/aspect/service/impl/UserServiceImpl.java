/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter4.aspect.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月11日 下午4:46:46   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter4.aspect.service.impl;

import org.springframework.stereotype.Service;
import com.lcclovehww.springboot.chapter4.aspect.service.UserService;
import com.lcclovehww.springboot.chapter4.jdbc.pojo.User;

/**   
 * @ClassName:  UserServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月11日 下午4:46:46   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class UserServiceImpl implements UserService {

	/**   
	 * <p>Title: printUser</p>   
	 * <p>Description: </p>   
	 * @param user   
	 * @see com.lcclovehww.springboot.chapter4.aspect.service.UserService#printUser(com.lcclovehww.springboot.chapter4.jdbc.pojo.User)   
	 */
	@Override
	public void printUser(User user) {
		if(user == null) {
			throw new RuntimeException("检查用户参数是否为空.......");
		}
		System.out.println("id="+user.getId());
		System.out.println("\tusername="+user.getUsername());
		System.out.println("\tnote="+user.getNote());;
	}

}
