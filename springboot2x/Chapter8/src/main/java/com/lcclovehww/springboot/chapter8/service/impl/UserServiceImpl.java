/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter8.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月2日 下午4:10:14   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter8.service.impl;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.lcclovehww.springboot.chapter8.pojo.User;
import com.lcclovehww.springboot.chapter8.service.UserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**   
 * @ClassName:  UserServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月2日 下午4:10:14   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private MongoTemplate mongoTmpl =null;
	/**   
	 * <p>Title: saveUser</p>   
	 * <p>Description: </p>   
	 * @param user   
	 * @see com.lcclovehww.springboot.chapter8.service.UserService#saveUser(com.lcclovehww.springboot.chapter8.pojo.User)   
	 */
	@Override
	public void saveUser(User user) {
		mongoTmpl.save(user,"user");
	}

	/**   
	 * <p>Title: deleteUser</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.lcclovehww.springboot.chapter8.service.UserService#deleteUser(java.lang.Long)   
	 */
	@Override
	public DeleteResult deleteUser(Long id) {
		Criteria criteriaId= Criteria.where("id").is(id);
		Query queryId = Query.query(criteriaId);
		DeleteResult result = mongoTmpl.remove(queryId, User.class);
		return result;
	}

	/**   
	 * <p>Title: findUser</p>   
	 * <p>Description: </p>   
	 * @param userName
	 * @param note
	 * @param skip
	 * @param limit
	 * @return   
	 * @see com.lcclovehww.springboot.chapter8.service.UserService#findUser(java.lang.String, java.lang.String, int, int)   
	 */
	@Override
	public List<User> findUser(String userName, String note, int skip, int limit) {
		Criteria criteria = Criteria.where("userName").regex(userName).and("note").regex(note);
		Query query = Query.query(criteria).limit(limit).skip(skip);
		List<User> userList = mongoTmpl.find(query, User.class);
		return userList;
	}

	/**   
	 * <p>Title: updateUser</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @param userName
	 * @param note
	 * @return   
	 * @see com.lcclovehww.springboot.chapter8.service.UserService#updateUser(java.lang.Long, java.lang.String, java.lang.String)   
	 */
	@Override
	public UpdateResult updateUser(Long id, String userName, String note) {
		Criteria criteriaId = Criteria.where("id").is(id);
		Query query = Query.query(criteriaId);
		Update update = Update.update("userName", userName);
		update.set("note", note);
		
		UpdateResult result = mongoTmpl.updateFirst(query, update, User.class);
		
		
		//UpdateResult result = mongoTmpl.updateMulti(query, update, User.class);
		return result;
	}

	/**   
	 * <p>Title: getUser</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.lcclovehww.springboot.chapter8.service.UserService#getUser(java.lang.Long)   
	 */
	@Override
	public User getUser(Long id) {
		return mongoTmpl.findById(id, User.class);
		//如果只需要获取第一个，也可以采用如下的查询方法
//		Criteria criteriaId = Criteria.where("id").is(id);
//		Query queryId = Query.query(criteriaId);
//		return mongoTmpl.findOne(queryId, User.class);
	}

}
