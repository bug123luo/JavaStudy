/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserBatchService.java   
 * @Package com.lcclovehww.springboot.chapter6.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月21日 下午4:31:30   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter6.service;

import com.lcclovehww.springboot.chapter6.pojo.User;

import java.util.List;

/**   
 * @ClassName:  UserBatchService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月21日 下午4:31:30   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface UserBatchService {
	public int insertUsers(List<User> userList);
}
