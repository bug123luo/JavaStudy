/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserRepository.java   
 * @Package com.lcclovehww.springboot.chapter8.repository   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月8日 下午2:00:54   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter8.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lcclovehww.springboot.chapter8.pojo.User;

/**   
 * @ClassName:  UserRepository   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月8日 下午2:00:54   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

	List<User> findByUserNameLike(String userName);
	
	User findUserByIdOrUserName(Long id, String userName);
}
