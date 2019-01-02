/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserService.java   
 * @Package com.lcclovehww.springboot.chapter8.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月2日 下午3:08:43   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter8.service;

import java.util.List;

import com.lcclovehww.springboot.chapter8.pojo.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;


/**   
 * @ClassName:  UserService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月2日 下午3:08:43   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface UserService {
	public void saveUser(User user);
	
	public DeleteResult deleteUser(Long id);
	
	public List<User> findUser(String userName, String note, int skip, int limit);
	
	public UpdateResult updateUser(Long id, String userName, String note);
	
	public User getUser(Long id);
}
