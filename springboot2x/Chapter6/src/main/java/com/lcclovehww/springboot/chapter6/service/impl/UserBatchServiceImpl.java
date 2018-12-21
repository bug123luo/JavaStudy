/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserBatchServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter6.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月21日 下午4:32:28   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter6.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.lcclovehww.springboot.chapter6.pojo.User;
import com.lcclovehww.springboot.chapter6.service.UserBatchService;
import com.lcclovehww.springboot.chapter6.service.UserService;

/**   
 * @ClassName:  UserBatchServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月21日 下午4:32:28   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class UserBatchServiceImpl implements UserBatchService {

	@Autowired
	private UserService userService = null;
	
	/**   
	 * <p>Title: insertUser</p>   
	 * <p>Description: </p>   
	 * @param userList
	 * @return   
	 * @see com.lcclovehww.springboot.chapter6.service.UserBatchService#insertUser(java.util.List)   
	 */
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public int insertUsers(List<User> userList) {
		int count = 0;
		for(User user:userList) {
			count += userService.insertUser(user);
		}
		return count;
	}

}
