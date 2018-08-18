package com.lcclovehww.mybatis;

import org.apache.ibatis.session.SqlSession;
import com.lcclovehww.jdbc.Role;

public class MyBatisExample {
	public static void main(String[] args) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = roleMapper.getRole(1L);
			System.err.println(role.getRole_name());
						
		} finally {
			sqlSession.close();
		}
	}
}
