/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  JdbcTmpUserService.java   
 * @Package com.lcclovehww.springboot.chapter5.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月12日 下午4:33:46   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.service;

import java.util.List;

import com.lcclovehww.springboot.chapter5.pojo.User;



/**   
 * @ClassName:  JdbcTmpUserService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月12日 下午4:33:46   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface JdbcTmpUserService {

	public User getUser(Long id);
	
	public List<User> findUsers(String userName, String note);
	
	public int insertUser(User user);
	
	public int updateUser(User user);
	
	public int deleteUser(Long id);
}
