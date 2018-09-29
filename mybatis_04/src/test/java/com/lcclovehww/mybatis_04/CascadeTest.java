/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  CascadeTest.java   
 * @Package com.lcclovehww.mybatis_04   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年9月29日 下午2:08:26   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.mybatis_04;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lcclovehww.mapper.StudentMapper;
import com.lcclovehww.pojo.Lecture;
import com.lcclovehww.pojo.Student;
import com.lcclovehww.pojo.StudentLecture;
import com.lcclovehww.util.SqlSessionFactoryUtil;

/**   
 * @ClassName:  CascadeTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年9月29日 下午2:08:26   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class CascadeTest {

	private static SqlSession sqlSession = null;
	/**   
	 * @Title: setUpBeforeClass   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @throws java.lang.Exception      
	 * @return: void      
	 * @throws   
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**   
	 * @Title: tearDownAfterClass   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @throws java.lang.Exception      
	 * @return: void      
	 * @throws   
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void Association() {
		
        try {
        	sqlSession = SqlSessionFactoryUtil.openSqlSession();
			StudentMapper stuMapper = sqlSession.getMapper(StudentMapper.class);
			Student stu = stuMapper.getStudent(1L);
	
			System.out.println(stu.getId()+" "+stu.getStuName()+" "+stu.getSelfcardNo()+" "+stu.getStudentSelfcard().getId());
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
	
	@Test
	public void CollectionTest() {
		try {
        	sqlSession = SqlSessionFactoryUtil.openSqlSession();
			StudentMapper stuMapper = sqlSession.getMapper(StudentMapper.class);
			Student stu = stuMapper.getStudent(1L);
			
			System.out.println(stu.getStudentSelfcard().getNativePlace());
			StudentLecture studentLecture = stu.getStudentLectureList().get(0);
			System.out.println(studentLecture.getId()+"\t"+studentLecture.getGrade());
			Lecture lecture = studentLecture.getLecture();

			System.out.println(stu.getStuName()+"\t"+lecture.getLectureName()+"\t"+studentLecture.getGrade());
			
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
			
		}
	}
	
}
