/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  AppConfig.java   
 * @Package com.lcclovehww.springboot.chapter2.config   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月6日 下午7:37:09   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter2.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Service;

/**   
 * @ClassName:  AppConfig   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月6日 下午7:37:09   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Configuration
//@ComponentScan("com.lcclovehww.springboot.chapter2.*")
//@ComponentScan(basePackages={"com.lcclovehww.springboot.chapter2.pojo"})
//@ComponentScan(basePackageClasses= {User.class})
@ComponentScan(basePackages="com.lcclovehww.springboot.chapter2.*", excludeFilters = {@Filter(classes = {Service.class})})
public class AppConfig {

/*	@Bean(name="user")
	public User initUser(){
		User user = new User();
		user.setId(1L);
		user.setUserName("user_name_1");
		user.setNote("note_1");
		return user;
	}*/
	
	@Bean(name ="dataSource")
	public DataSource getDataSource() {
		Properties prop = new Properties();
		prop.setProperty("driver", "com.mysql.jdbc.Driver");
		prop.setProperty("url", "jdbc:mysql://localhost:3306/chapter3");
		prop.setProperty("username", "root");
		prop.setProperty("password", "123456");
		
		DataSource dataSource = null;
		try {
			dataSource = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dataSource;
	}
}
