package com.lcclovehww.mybatis_02;

import org.apache.ibatis.session.SqlSession;

import com.lcclovehww.mapper.RoleMapper;
import com.lcclovehww.pojo.Role;
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
			Role role = new Role();
			role.setRoleName("testName");
			role.setRemark("testNode");
			/*role.setId(2L);*/
			roleMapper.insertRole(role);
			roleMapper.deleteRole(1L);
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
