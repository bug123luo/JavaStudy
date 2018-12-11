/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserService.java   
 * @Package com.lcclovehww.springboot.chapter4.jdbc   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月11日 下午4:22:44   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter4.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.lcclovehww.springboot.chapter4.jdbc.pojo.User;

/**   
 * @ClassName:  UserService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月11日 下午4:22:44   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class UserService {
	public int insertUser() {
		UserDao userDao = new UserDao();
		User user = new User();
		user.setUsername("user_name_1");
		user.setNote("note_1");
		Connection conn = null;
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection("jdbc:mysql://112.74.51.194:3306/hww", "root", "123456");
			conn.setAutoCommit(false);
			result = userDao.insertUser(conn, user);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return result;
	}
}
