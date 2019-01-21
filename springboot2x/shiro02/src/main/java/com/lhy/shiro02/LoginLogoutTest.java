/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  LoginLogoutTest.java   
 * @Package com.lhy.shiro02   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年1月21日 下午3:52:43   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lhy.shiro02;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;
import junit.framework.Assert;

/**   
 * @ClassName:  LoginLogoutTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年1月21日 下午3:52:43   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class LoginLogoutTest {

    @Test
    public void testHellowworld() {
    	//1.获取SecurityManager工厂，此处用ini配置文件初始化ScurityManager
    	Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
    	//2.得到SecurityManager实例并绑定给SecurityUtils
    	SecurityManager securityManager =  factory.getInstance();
    	SecurityUtils.setSecurityManager(securityManager);
    	//3.得到Subject及创建用户名/密码身份认证Token(即用户身份/凭证)
    	Subject subject = SecurityUtils.getSubject();
    	UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
    	try {
    		//4.登录，即身份验证
			subject.login(token);
		}catch (AuthenticationException e1) {
			//5.身份验证失败
		}/* catch (DisabledAccountException e2) {
			//6.禁用的账号
		}catch (LockedAccountException e3) {
			//7.锁定的账号
		}catch (UnknownAccountException e4) {
			//8.错误的账号
		}catch (ExcessiveAttemptsException e5) {
			//9.登录失败次数过多
		}catch (IncorrectCredentialsException  e6) {
			//10.错误的凭证
		}catch (ExpiredCredentialsException e7) {
			//11.过期的凭证
		}*/
    	
    	Assert.assertEquals(true, subject.isAuthenticated());//断言用户已经登录
    	
    	//6.退出 
    	subject.logout();
    }
    
    @Test
    public void testCustomRealm() {
    	//1.获取SecurityManager工厂，此处用ini配置文件初始化ScurityManager
    	Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
    	//2.得到SecurityManager实例并绑定给SecurityUtils
    	SecurityManager securityManager= factory.getInstance();
    	SecurityUtils.setSecurityManager(securityManager);
    	//3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
    	Subject subject =  SecurityUtils.getSubject();
    	UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
    	
    	try {
    		//4.登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			//5.身份验证失败
			e.printStackTrace();
		}
    	
    	Assert.assertEquals(true, subject.isAuthenticated());//断言用户已经登录
    	
    	//6.退出
    	subject.logout();
    }

    @Test
    public void testCustomMultiRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }
    
    @Test
    public void testJDBCRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }

    
    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }
}
