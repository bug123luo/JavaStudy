/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  JpaController.java   
 * @Package com.lcclovehww.springboot.chapter5.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月13日 上午10:20:39   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lcclovehww.springboot.chapter5.dao.JpaUserRepository;
import com.lcclovehww.springboot.chapter5.pojo.PersistenceUser;

/**   
 * @ClassName:  JpaController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月13日 上午10:20:39   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Controller
@RequestMapping("/jpa")
public class JpaController {
	@Autowired
	private JpaUserRepository jpaUserRepository = null;
	
	@RequestMapping("/getUser")
	@ResponseBody
	public PersistenceUser getUser(Long id) {
		PersistenceUser user= jpaUserRepository.findById(id).get();
		return user;
	}
	
	@RequestMapping("/getUserById")
	@ResponseBody
	public PersistenceUser getUserById(Long id) {
		PersistenceUser user = jpaUserRepository.getUserById(id);
		return user;
	}
	
	@RequestMapping("/findByUserNameLike")
	@ResponseBody
	public List<PersistenceUser> findByUserNameLike(String userName){
		List<PersistenceUser> userList = jpaUserRepository.findByUserNameLike("%"+userName+"%");
		return userList;
	}
	
	@RequestMapping("/findByUserNameLikeOrNoteLike")
	@ResponseBody
	public List<PersistenceUser> findByUserNameLikeOrNoteLike(String userName, String note){
		String userNameLike = "%"+userName+"%";
		String noteLike ="%"+note+"%";
		
		List<PersistenceUser> userList = jpaUserRepository.findByUserNameLikeOrNoteLike(userNameLike, noteLike);
		return userList;
	}
}
