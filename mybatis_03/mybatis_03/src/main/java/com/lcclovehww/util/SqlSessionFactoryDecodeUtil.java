/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  SqlSessionFactoryDecodeUtil.java   
 * @Package com.lcclovehww.util   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月20日 上午11:17:05   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hibernate.sql.DecodeCaseFragment;

/**   
 * @ClassName:  SqlSessionFactoryDecodeUtil   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月20日 上午11:17:05   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class SqlSessionFactoryDecodeUtil {
	private static SqlSessionFactory sqlSessionFactory=null;
	
	private static final Class CLASS_LOCK=SqlSessionFactoryDecodeUtil.class;
	
	private SqlSessionFactoryDecodeUtil()	{}
	
	public static SqlSessionFactory initSqlSessionFactory() {
		
		String resource = "mybatis_config.xml";
		InputStream cfgStream = null;
		Reader cfgReader= null;
		InputStream proStream = null;
		Reader proReader = null;
		Properties properties = null;
		
		try {
			cfgStream =  Resources.getResourceAsStream(resource);
			cfgReader = new InputStreamReader(cfgStream);
			proStream = Resources.getResourceAsStream("jdbc.properties");
			proReader = new InputStreamReader(proStream);
			properties = new Properties();
			properties.load(proReader);
			
			properties.setProperty("username",decode(properties.getProperty("username")));
			properties.setProperty("password", decode(properties.getProperty("password")));
		} catch (Exception e) {
			Logger.getLogger(SqlSessionFactoryDecodeUtil.class.getName()).log(Level.SEVERE, null, e);
		}
		
		synchronized (CLASS_LOCK) {
			if(sqlSessionFactory ==null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(cfgReader,properties);
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
	
	public static String decode(String decStr) {
		return decStr;
	}
}
