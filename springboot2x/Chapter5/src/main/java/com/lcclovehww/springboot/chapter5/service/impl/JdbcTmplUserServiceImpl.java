/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  JdbcTmplUserServiceImpl.java   
 * @Package com.lcclovehww.springboot.chapter5.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月12日 下午4:36:59   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter5.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.lcclovehww.springboot.chapter5.enumeration.SexEnum;
import com.lcclovehww.springboot.chapter5.pojo.User;
import com.lcclovehww.springboot.chapter5.service.JdbcTmpUserService;

/**   
 * @ClassName:  JdbcTmplUserServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月12日 下午4:36:59   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class JdbcTmplUserServiceImpl implements JdbcTmpUserService {

	@Autowired
	private JdbcTemplate jdbcTemplate = null;
	
	private RowMapper<User> getUserMapper(){
		RowMapper<User> userRowMapper = (ResultSet rs, int rownum)->{
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setUserName(rs.getString("user_name"));
			user.setNote(rs.getString("note"));
			int sexId = rs.getInt("sex");
			SexEnum sex = SexEnum.getEnumById(sexId);
			user.setSex(sex);
			return user;
		};
		
		return userRowMapper;
	}
	
	/**   
	 * <p>Title: getUser</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.lcclovehww.springboot.chapter5.service.JdbcTmpUserService#getUser(java.lang.Long)   
	 */
	@Override
	public User getUser(Long id) {
		String sql = "select id, user_name, sex, note from t_user where id = ?";
		Object[] params = new Object[] {id};
		User user = jdbcTemplate.queryForObject(sql, params, getUserMapper());
		return user;
	}

	/**   
	 * <p>Title: findUsers</p>   
	 * <p>Description: </p>   
	 * @param userName
	 * @param note
	 * @return   
	 * @see com.lcclovehww.springboot.chapter5.service.JdbcTmpUserService#findUsers(java.lang.String, java.lang.String)   
	 */
	@Override
	public List<User> findUsers(String userName, String note) {
		String sql ="select id, user_name, sex, note from t_user"
					+"where user_name like concat('%',?,'%')"
					+"and note like concat('%', ?, '%')";
		
		Object[] params = new Object[] {userName, note};
		
		List<User> userList = jdbcTemplate.query(sql, params,getUserMapper());
		return userList;
	}

	/**   
	 * <p>Title: insertUser</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return   
	 * @see com.lcclovehww.springboot.chapter5.service.JdbcTmpUserService#insertUser(org.apache.catalina.User)   
	 */
	@Override
	public int insertUser(User user) {
		String sql = "insert into t_user(user_name, sex, note) values(?, ?, ?)";
		
		return jdbcTemplate.update(sql, user.getUserName(),user.getSex().getId(),user.getNote());
	}

	/**   
	 * <p>Title: updateUser</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return   
	 * @see com.lcclovehww.springboot.chapter5.service.JdbcTmpUserService#updateUser(org.apache.catalina.User)   
	 */
	@Override
	public int updateUser(User user) {
		String sql ="update t_user set user_name=?, sex=?, note=?"
				+"where id=?";
		return jdbcTemplate.update(sql, user.getUserName(),user.getSex().getId(),user.getNote(),user.getId());
	}

	/**   
	 * <p>Title: deleteUser</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.lcclovehww.springboot.chapter5.service.JdbcTmpUserService#deleteUser(java.lang.Long)   
	 */
	@Override
	public int deleteUser(Long id) {
		String sql = "delete from t_user where id=?";
		return jdbcTemplate.update(sql,id);
	}

	public User getUser2(Long id) {
		User result = this.jdbcTemplate.execute((Statement stmt) -> {
					String sql1="select count(*) total from t_user where id= "+id;
					ResultSet rs1 = stmt.executeQuery(sql1);
					while(rs1.next()) {
						int total =  rs1.getInt("total");
						System.out.println(total);
					}
					
					String sql2 = "select id, user_name, sex, note from t_user"
								+ " where id= "+id;
					ResultSet rs2 = stmt.executeQuery(sql2);
					User user = null;
					while(rs2.next()) {
						int rowNum = rs2.getRow();
						user = getUserMapper().mapRow(rs2, rowNum);
					}
					return user;
		});
		return result;
	}
	
	public User getUser3(Long id) {
		return this.jdbcTemplate.execute((Connection conn) -> {
			String sql1 = "select count(*) as total from t_user"+"where id=?";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setLong(1, id);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()) {
				System.out.println(rs1.getInt("total"));
			}
			String sql2 =  "select id, user_name, sex, note from t_user "+"where id=?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setLong(1, id);
			ResultSet rs2 = ps2.executeQuery();
			User user= null;
			while(rs2.next()) {
				int rowNum = rs2.getRow();
				user = getUserMapper().mapRow(rs2, rowNum);
			}
			return user;
		});
	}

}
