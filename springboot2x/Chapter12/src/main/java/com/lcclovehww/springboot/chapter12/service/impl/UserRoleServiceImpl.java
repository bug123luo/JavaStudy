/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserRoleServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter12.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月22日 下午2:58:07   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter12.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcclovehww.springboot.chapter12.dao.RoleDao;
import com.lcclovehww.springboot.chapter12.dao.UserDao;
import com.lcclovehww.springboot.chapter12.pojo.DatabaseRole;
import com.lcclovehww.springboot.chapter12.pojo.DatabaseUser;
import com.lcclovehww.springboot.chapter12.service.UserRoleService;

/**   
 * @ClassName:  UserRoleServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月22日 下午2:58:07   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private UserDao userDao=null;
	@Autowired
	private RoleDao roleDao=null;
	
	/**   
	 * <p>Title: getUserByName</p>   
	 * <p>Description: </p>   
	 * @param userName
	 * @return   
	 * @see com.lcclovehww.springboot.chapter12.service.UserRoleService#getUserByName(java.lang.String)   
	 */
	@Override
	@Cacheable(value="redisCache",key="'redis_user_'+#userName")
	@Transactional
	public DatabaseUser getUserByName(String userName) {
		return userDao.getUser(userName);
	}

	/**   
	 * <p>Title: findRolesByUserName</p>   
	 * <p>Description: </p>   
	 * @param userName
	 * @return   
	 * @see com.lcclovehww.springboot.chapter12.service.UserRoleService#findRolesByUserName(java.lang.String)   
	 */
	@Override
	@Cacheable(value="redisCahce",key="'redis_user_role_'+#userName")
	@Transactional
	public List<DatabaseRole> findRolesByUserName(String userName) {
		return roleDao.findRolesByUserName(userName);
	}

}
