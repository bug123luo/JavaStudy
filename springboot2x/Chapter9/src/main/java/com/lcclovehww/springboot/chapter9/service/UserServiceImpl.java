/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter9.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年2月22日 上午11:30:40   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter9.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.lcclovehww.springboot.chapter9.dao.UserDao;
import com.lcclovehww.springboot.chapter9.service.UserService;
import com.lcclovehww.springboot.chapter9.pojo.User;

/**   
 * @ClassName:  UserServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年2月22日 上午11:30:40   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao=null;
	
	@Autowired
	private ApplicationContext applicationContext = null;
	
	/**   
	 * <p>Title: getUser</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.lcclovehww.springboot.chapter6.service.UserService#getUser(java.lang.Long)   
	 */
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,timeout=1)
	public User getUser(Long id) {
		return userDao.getUser(id);
	}

	/**   
	 * <p>Title: insertUser</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return   
	 * @see com.lcclovehww.springboot.chapter6.service.UserService#insertUser(com.lcclovehww.springboot.chapter6.pojo.User)   
	 */
	@Override
	//@Transactional(isolation=Isolation.READ_COMMITTED, timeout=1)
	//@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.NESTED)
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	/**   
	 * <p>Title: insertUsers</p>   
	 * <p>Description: </p>   
	 * @param userList
	 * @return   
	 * @see com.lcclovehww.springboot.chapter6.service.UserService#insertUsers(java.util.List)   
	 */
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public int insertUsers(List<User> userList) {
		int count = 0;
		UserService userService = applicationContext.getBean(UserService.class);
		for(User user:userList) {
			count += userService.insertUser(user);
		}
		return count;
	}

}
