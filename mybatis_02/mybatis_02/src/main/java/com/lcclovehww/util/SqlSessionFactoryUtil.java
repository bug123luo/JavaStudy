/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  SqlSessionFactoryUtil.java   
 * @Package com.lcclovehww.util   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月20日 上午9:54:32   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.util;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**   
 * @ClassName:  SqlSessionFactoryUtil   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月20日 上午9:54:32   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class SqlSessionFactoryUtil {

	private static SqlSessionFactory sqlSessionFactory=null;
	
	private static final Class CLASS_LOCK=SqlSessionFactoryUtil.class;
	
	private SqlSessionFactoryUtil()	{}
	
	public static SqlSessionFactory initSqlSessionFactory() {
		
		String resource = "mybatis_config.xml";
		InputStream inputStream = null;
		
		try {
			inputStream =  Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.SEVERE, null, e);
		}
		
		synchronized (CLASS_LOCK) {
			if(sqlSessionFactory ==null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			}
			
		}
		
		return sqlSessionFactory;
		
	}
	
	public static SqlSession openSqlSession() {
		if(sqlSessionFactory == null) {
			initSqlSessionFactory();
		}
		
		return sqlSessionFactory.openSession();
	}
}
