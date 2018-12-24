/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter7.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月24日 下午5:25:19   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter7.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcclovehww.springboot.chapter7.dao.UserDao;
import com.lcclovehww.springboot.chapter7.pojo.User;
import com.lcclovehww.springboot.chapter7.service.UserService;

/**   
 * @ClassName:  UserServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月24日 下午5:25:19   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao = null;
	
	/**   
	 * <p>Title: getUser</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.lcclovehww.springboot.chapter7.service.UserService#getUser(java.lang.Long)   
	 */
	@Override
	@Transactional
	@Cacheable(value="redisCache", key="'redis_user_'+#id")
	public User getUser(Long id) {
		return userDao.getUser(id);
	}

	/**   
	 * <p>Title: insertUser</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return   
	 * @see com.lcclovehww.springboot.chapter7.service.UserService#insertUser(com.lcclovehww.springboot.chapter7.pojo.User)   
	 */
	@Override
	@Transactional
	@CachePut(value="redisCache", key="'redis_user_'+#result.id")
	public User insertUser(User user) {
		userDao.insertUser(user);
		return user;
	}

	/**   
	 * <p>Title: updateUserName</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @param userName
	 * @return   
	 * @see com.lcclovehww.springboot.chapter7.service.UserService#updateUserName(java.lang.Long, java.lang.String)   
	 */
	@Override
	@Transactional
	@CachePut(value="redisCache",
			condition="'#result != 'null'",key="'redis_user_'+#id")
	public User updateUserName(Long id, String userName) {
		User user = this.getUser(id);
		if(user == null ) {
			return null;
		}
		user.setUserName(userName);
		userDao.updateUser(user);
		return user;
	}

	/**   
	 * <p>Title: findUsers</p>   
	 * <p>Description: </p>   
	 * @param userName
	 * @param note
	 * @return   
	 * @see com.lcclovehww.springboot.chapter7.service.UserService#findUsers(java.lang.String, java.lang.String)   
	 */
	@Override
	@Transactional
	public List<User> findUsers(String userName, String note) {
		return userDao.findUser(userName, note);
	}

	/**   
	 * <p>Title: deleteUser</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.lcclovehww.springboot.chapter7.service.UserService#deleteUser(java.lang.Long)   
	 */
	@Override
	@Transactional
	@CacheEvict(value="redisCache", key="'redis_user_'+#id",
				beforeInvocation = false)
	public int deleteUser(Long id) {
		return userDao.deleteUser(id);
	}

}
