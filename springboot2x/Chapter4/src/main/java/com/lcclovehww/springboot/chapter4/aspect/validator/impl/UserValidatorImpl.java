/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserValidatorImpl.java   
 * @Package com.lcclovehww.springboot.chapter4.aspect.validator.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月11日 下午8:32:58   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter4.aspect.validator.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lcclovehww.springboot.chapter4.aspect.validator.UserValidator;
import com.lcclovehww.springboot.chapter4.jdbc.pojo.User;

/**   
 * @ClassName:  UserValidatorImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月11日 下午8:32:58   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class UserValidatorImpl implements UserValidator {

	/**   
	 * <p>Title: validate</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return   
	 * @see com.lcclovehww.springboot.chapter4.aspect.validator.UserValidator#validate(com.lcclovehww.springboot.chapter4.jdbc.pojo.User)   
	 */
	@Override
	public boolean validate(User user) {
		System.out.println("引入新的接口: "+UserValidator.class.getSimpleName());
		return user!=null;
	}

}
