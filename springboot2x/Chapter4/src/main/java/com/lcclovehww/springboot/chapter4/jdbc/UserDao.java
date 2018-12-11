/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  UserDao.java   
 * @Package com.lcclovehww.springboot.chapter4.jdbc   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月11日 下午4:16:42   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter4.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lcclovehww.springboot.chapter4.jdbc.pojo.User;

/**   
 * @ClassName:  UserDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月11日 下午4:16:42   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class UserDao {

	public int insertUser(Connection conn, User user) throws SQLException{
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement("insert into t_user(username, note) values(?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getNote());
			return ps.executeUpdate();
		} catch (Exception e) {
			ps.close();
		}
		return 0;
	}
	
}
