package com.lcclovehww.mybatis_04;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.lcclovehww.mapper.RoleMapper;
import com.lcclovehww.mapper.StudentMapper;
import com.lcclovehww.pojo.Role;
import com.lcclovehww.pojo.Student;
import com.lcclovehww.util.SqlSessionFactoryUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SqlSession sqlSession = null;
        try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Map<String, String> paramsMap=new HashMap<String,String>();
			paramsMap.put("roleName", "me");
			paramsMap.put("remark", "te");
			List<Role> roles=roleMapper.findRoleByMap(paramsMap);
			for(Role role2:roles) {
				System.out.println(role2.getId());
				System.out.println(role2.getRemark());
				System.out.println(role2.getRoleName());
				
			}	
			
			String role_name="test";
			String remark="te";
			List<Role> role2s=roleMapper.findRoleByAnnotation(role_name, remark);
			for(Role role2:role2s) {
				System.out.println(role2.getId());
				System.out.println(role2.getRemark());
				System.out.println(role2.getRoleName());
				
			}
			
			Role role = new Role();
			role.setRoleName("test4");
			role.setRemark("test4Node");
			roleMapper.insertRoleGetKey(role);
			System.err.println(role.getId());
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
