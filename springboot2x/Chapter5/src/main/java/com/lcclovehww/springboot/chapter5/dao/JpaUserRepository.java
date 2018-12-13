/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  JpaUserRepository.java   
 * @Package com.lcclovehww.springboot.chapter5.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月13日 上午10:14:42   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lcclovehww.springboot.chapter5.pojo.PersistenceUser;


/**   
 * @ClassName:  JpaUserRepository   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月13日 上午10:14:42   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

public interface JpaUserRepository extends JpaRepository<PersistenceUser, Long> {
	
	@Query("from user where user_name like concat('%',?1,'%')"+"and note like concat('',?2,'%')")
	public List<PersistenceUser> findUsers(String userName, String note);
	
	List<PersistenceUser> findByUserNameLike(String userName);
	
	PersistenceUser getUserById(Long id);
	
	List<PersistenceUser> findByUserNameLikeOrNoteLike(String userName, String note);
}
