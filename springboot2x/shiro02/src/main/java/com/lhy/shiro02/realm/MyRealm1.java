/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MyRealm1.java   
 * @Package com.lhy.shiro02.realm   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月21日 下午3:54:12   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lhy.shiro02.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**   
 * @ClassName:  MyRealm1   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月21日 下午3:54:12   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class MyRealm1 implements Realm{

	/**   
	 * <p>Title: getName</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see org.apache.shiro.realm.Realm#getName()   
	 */
	public String getName() {
		return "myrealm1";
	}

	/**   
	 * <p>Title: supports</p>   
	 * <p>Description: </p>   
	 * @param token
	 * @return   
	 * @see org.apache.shiro.realm.Realm#supports(org.apache.shiro.authc.AuthenticationToken)   
	 */
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	/**   
	 * <p>Title: getAuthenticationInfo</p>   
	 * <p>Description: </p>   
	 * @param token
	 * @return
	 * @throws AuthenticationException   
	 * @see org.apache.shiro.realm.Realm#getAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)   
	 */
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username =  (String)token.getPrincipal();//得到用户名
		String password = new String((char[])token.getCredentials());//得到密码
		if(!"zhang".equals(username)) {
			throw new UnknownAccountException();//如果用户名错误
		}
		if(!"123".equals(password)) {
			throw new IncorrectCredentialsException();//如果密码错误
		}
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	
}
