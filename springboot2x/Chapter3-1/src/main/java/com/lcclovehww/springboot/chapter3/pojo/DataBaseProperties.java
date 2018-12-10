/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  DataBaseProperties.java   
 * @Package com.lcclovehww.springboot.chapter3.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月10日 下午4:21:22   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter3.pojo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**   
 * @ClassName:  DataBaseProperties   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月10日 下午4:21:22   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Component
public class DataBaseProperties {

	@Value("${database.drivrName}")
	private String driverName= null;
	
	@Value("${database.url}")
	private String url=null;
	
	private String username = null;
	
	private String password = null;
	
	public void setDriverName(String driverName) {
		System.out.println(driverName);
		this.driverName = driverName;
	}
	
	public void setUrl(String url) {
		System.out.println(url);
		this.url =url;
	}
	
	@Value("${database.username}")
	public void setUsername(String username) {
		System.out.println(username);
		this.username = username;
	}
	
	@Value("${database.password}")
	public void setPassword(String password) {
		System.out.println(password);
		this.password = password;
	}
}
