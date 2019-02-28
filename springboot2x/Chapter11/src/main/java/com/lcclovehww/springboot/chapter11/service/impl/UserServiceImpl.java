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
package com.lcclovehww.springboot.chapter11.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcclovehww.springboot.chapter11.dao.UserDao;
import com.lcclovehww.springboot.chapter11.pojo.User;
import com.lcclovehww.springboot.chapter11.service.UserService;

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
	
	@Override
	public User getUser(Long id) {
		return userDao.getUser(id);
	}

	@Override
	public List<User> findUsers(String userName, String note, int start, int limit) {
		return userDao.findUsers(userName, note, start, limit);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public int updateUserName(Long id, String userName) {
		return userDao.updateUserName(id, userName);
	}

	@Override
	public int deleteUser(Long id) {
		return userDao.deleteUser(id);
	}

	@Override
	public User insertUser(User user) {
		return userDao.insertUser(user) >0 ? user  : null;
	}

}
