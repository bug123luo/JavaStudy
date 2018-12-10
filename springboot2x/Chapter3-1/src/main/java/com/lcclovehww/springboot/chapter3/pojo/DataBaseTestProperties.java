/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  DataBaseTestProperties.java   
 * @Package com.lcclovehww.springboot.chapter3.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月10日 下午4:40:46   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter3.pojo;

import static org.hamcrest.CoreMatchers.nullValue;

import javax.print.DocFlavor.STRING;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**   
 * @ClassName:  DataBaseTestProperties   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月10日 下午4:40:46   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Component
@ConfigurationProperties("database")
public class DataBaseTestProperties {
	
	private String driverName=null;
	
	private String url=null;
	
	private String username=null;
	
	private String password=null;
	
	public void setDriverName(String driverName) {
		System.out.println(driverName);
		this.driverName = driverName;
	}
	
	public void setUrl(String url) {
		System.out.println(url);
		this.url=url;
	}
	
	public void setUsername(String username) {
		System.out.println(username);
		this.username = username;
	}
	
	public void setPassword(String password) {
		System.out.println(password);
		this.password = password;
	}
}
