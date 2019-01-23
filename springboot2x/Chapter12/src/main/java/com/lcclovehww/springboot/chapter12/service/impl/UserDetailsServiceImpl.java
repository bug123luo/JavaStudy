/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserDetailsServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter12   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月22日 下午2:34:57   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter12.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcclovehww.springboot.chapter12.pojo.DatabaseRole;
import com.lcclovehww.springboot.chapter12.pojo.DatabaseUser;
import com.lcclovehww.springboot.chapter12.service.UserRoleService;

/**   
 * @ClassName:  UserDetailsServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月22日 下午2:34:57   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRoleService userRoleService =null;
	
	/**   
	 * <p>Title: loadUserByUsername</p>   
	 * <p>Description: </p>   
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException   
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)   
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//获取数据库用户信息
		DatabaseUser dbUser = userRoleService.getUserByName(username);
		//获取数据库角色信息
		List<DatabaseRole> roleList = userRoleService.findRolesByUserName(username);
		
		return changeToUser(dbUser, roleList);
	}
	
	private UserDetails changeToUser(DatabaseUser dbUser, List<DatabaseRole> roleList) {
		//权限列表
		List<GrantedAuthority> authorityList = new ArrayList<>();
		//赋予查询到的角色
		for(DatabaseRole role:roleList) {
			GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
			authorityList.add(authority);
		}
		
		UserDetails userDetails = new User(dbUser.getUserName(), dbUser.getPwd(), authorityList);
		return userDetails;
	}

}
