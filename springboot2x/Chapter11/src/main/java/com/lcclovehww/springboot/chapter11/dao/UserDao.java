/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserDao.java   
 * @Package com.lcclovehww.springboot.chapter7.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月24日 下午5:04:49   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter11.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.lcclovehww.springboot.chapter11.pojo.User;

/**   
 * @ClassName:  UserDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月24日 下午5:04:49   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Repository
public interface UserDao {
	
	public User getUser(Long id);
	
	public int insertUser(User user);

	public List<User> findUsers(@Param("userName") String userName, @Param("note") String note, @Param("start") int start,  @Param("limit") int limit);
	
	public int updateUser(User user);
	
	public int updateUserName(@Param("id") Long id, @Param("userName") String userName);
	
	public int deleteUser(Long id);
}
