package com.lcclovehww.mybatis_03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.lcclovehww.enums.Sex;
import com.lcclovehww.mapper.RoleMapper;
import com.lcclovehww.mapper.UserMapper;
import com.lcclovehww.pojo.Role;
import com.lcclovehww.pojo.User;
import com.lcclovehww.util.SqlSessionFactoryUtil;

/**
 * Hello world!
 *
 */
public class App 
{
/*    public static void main( String[] args )
    {
        SqlSession sqlSession = null;
        try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			String roleName="test";
			List<Role> roles=roleMapper.findRole(roleName);
			for(Role role2:roles) {
				System.out.println(role2.getId());
				System.out.println(role2.getRemark());
				System.out.println(role2.getRoleName());		
			}			
			
			sqlSession.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			sqlSession.rollback();
		}finally {
			if(sqlSession !=null) {
				sqlSession.close();
			}
		}
    }*/
	
	 public static void main( String[] args ) {
	        SqlSession sqlSession = null;
	        try {
	        	sqlSession = SqlSessionFactoryUtil.openSqlSession();
	        	UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	        	User user = new User();
	        	user.setUserName("zhangsan");
	        	user.setNickname("张三");
	        	user.setMobile("18888888888");
	        	user.setSex(Sex.MALE);
	        	user.setEmail("zhangsan@163.com");
	        	user.setRemark("test EnumOrdinalTypeHandler!!");
	        	userMapper.insertUser(user);
	        	
	        	User user2 = userMapper.getUser(1L);
	        	System.out.println(user2.getSex());
	        	sqlSession.commit();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				sqlSession.rollback();
			}finally {
				if(sqlSession !=null) {
					sqlSession.close();
				}
			}
	 }
}
