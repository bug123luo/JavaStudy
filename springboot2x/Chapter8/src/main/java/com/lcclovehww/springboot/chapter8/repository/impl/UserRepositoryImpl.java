/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserRepositoryImpl.java   
 * @Package com.lcclovehww.springboot.chapter8.repository.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月8日 下午2:14:30   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter8.repository.impl;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lcclovehww.springboot.chapter8.pojo.User;

/**   
 * @ClassName:  UserRepositoryImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月8日 下午2:14:30   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Repository
public class UserRepositoryImpl {
	@Autowired
	private MongoTemplate mongoTemplate = null;
	
	User findUserByIdOrUserName(Long id, String userName) {
		Criteria criteriaId = Criteria.where("id").is(id);
		Criteria criteriaUserName = Criteria.where("userName").is(userName);
		Criteria criteria = new Criteria();
		criteria.orOperator(criteriaId,criteriaUserName);
		Query query = Query.query(criteria);
		return mongoTemplate.findOne(query, User.class);
	}
}
