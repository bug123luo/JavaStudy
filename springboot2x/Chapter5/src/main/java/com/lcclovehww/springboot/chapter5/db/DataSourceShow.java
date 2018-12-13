/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  DataSourceShow.java   
 * @Package com.lcclovehww.springboot.chapter5.db   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月12日 下午3:57:51   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.db;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**   
 * @ClassName:  DataSourceShow   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月12日 下午3:57:51   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Component
public class DataSourceShow implements ApplicationContextAware {

	ApplicationContext applicationContext = null;
	
	/**   
	 * <p>Title: setApplicationContext</p>   
	 * <p>Description: </p>   
	 * @param applicationContext
	 * @throws BeansException   
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)   
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		System.out.println("----------------------------------------------");
		System.out.println(dataSource.getClass().getName());
		System.out.println("----------------------------------------------");

	}

}
