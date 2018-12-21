/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  JdbcServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter6.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月21日 上午10:12:13   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter6.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcclovehww.springboot.chapter6.service.JdbcService;

/**   
 * @ClassName:  JdbcServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月21日 上午10:12:13   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Service
public class JdbcServiceImpl implements JdbcService {

	@Autowired
	private DataSource dataSource = null;
	
	/**   
	 * <p>Title: inserUser</p>   
	 * <p>Description: </p>   
	 * @param userName
	 * @param note
	 * @return   
	 * @see com.lcclovehww.springboot.chapter6.service.JdbcService#inserUser(java.lang.String, java.lang.String)   
	 */
	@Override
	public int insertUser(String userName, String note) {
		Connection conn=null;
		int result =0;
		try {
			conn = dataSource.getConnection();
			//开启事务
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(TransactionIsolationLevel.READ_COMMITTED.getLevel());
			PreparedStatement ps = conn.prepareStatement("insert into t_user(user_name, note) values(?,?)");
			ps.setString(1, userName);
			ps.setString(2, note);
			result = ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}
