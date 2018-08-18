package com.lcclovehww.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcExample {
	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://112.74.51.194:3306/mybatis?zeroDateTimeBehavior=convertToNull";
			String user = "root";
			String password = "123456";
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getLogger(JdbcExample.class.getName()).log(Level.SEVERE,null,e);
			return null;
		}
		return connection;
	}
	
	public Role getRole(Long id) {
		Connection connection = getConnection();
		PreparedStatement ps= null;
		ResultSet rs = null;
		
		try {
			ps=connection.prepareStatement("select id,role_name,remark from role where id=?");
			ps.setLong(1, id);
			rs=ps.executeQuery();
			while (rs.next()) {
				Long roleId=rs.getLong("id");
				String userName = rs.getString("role_name");
				String remark= rs.getString("remark");
				Role role = new Role();
				role.setId(roleId);
				role.setRemark(remark);
				role.setRole_name(userName);
				return role;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcExample.class.getName()).log(Level.SEVERE,null,e);
		}finally {
			this.close(rs,ps,connection);
		}
		return null;
	}
	
	private void close(ResultSet rs,Statement stmt, Connection connection) {
		try {
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {
			Logger.getLogger(JdbcExample.class.getName()).log(Level.SEVERE,null,e);
		}
		
		try {
			if(stmt!=null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {
			Logger.getLogger(JdbcExample.class.getName()).log(Level.SEVERE,null,e);
		}
		
		try {
			if (connection !=null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			Logger.getLogger(JdbcExample.class.getName()).log(Level.SEVERE,null,e);
		}
	}
	
	public static void main(String[] args) {
		JdbcExample example = new JdbcExample();
		Role role=example.getRole(1L);
		System.err.println("role_name = >"+role.getRole_name());
	}
}
