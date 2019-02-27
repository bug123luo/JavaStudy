/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserService.java   
 * @Package com.lcclovehww.springboot.chapter7.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月24日 下午5:20:04   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter11.service;

import java.util.List;

import com.lcclovehww.springboot.chapter11.pojo.User;

/**   
 * @ClassName:  UserService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月24日 下午5:20:04   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface UserService {
	//获取单个用户
	User getUser(Long id);
	//保存用户
	User insertUser(User user);
	//修改用户,指定Mybatis的参数名称
	User updateUserName(Long id, String userName);
	//查询用户，指定MyBatis的参数名称
	List<User> findUsers(String userName, String note);
	//删除用户
	int deleteUser(Long id);
}
