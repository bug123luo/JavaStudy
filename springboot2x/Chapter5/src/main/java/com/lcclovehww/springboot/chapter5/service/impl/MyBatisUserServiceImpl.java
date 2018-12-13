/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MyBatisUserServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter5.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月13日 下午3:08:00   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcclovehww.springboot.chapter5.dao.MyBatisUserDao;
import com.lcclovehww.springboot.chapter5.pojo.MybatisUser;
import com.lcclovehww.springboot.chapter5.service.MyBatisUserService;

/**   
 * @ClassName:  MyBatisUserServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月13日 下午3:08:00   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class MyBatisUserServiceImpl implements MyBatisUserService{

	@Autowired
	private MyBatisUserDao myBatisUserDao = null;
	
	/**   
	 * <p>Title: getUser</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.lcclovehww.springboot.chapter5.service.MyBatisUserService#getUser(java.lang.Long)   
	 */
	@Override
	public MybatisUser getUser(Long id) {
		return myBatisUserDao.getUser(id);
	}

}
