/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Student.java   
 * @Package com.lcclovehww.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月20日 下午5:22:19   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.pojo;

import java.util.List;

import com.lcclovehww.enums.Sex;

/**   
 * @ClassName:  Student   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月20日 下午5:22:19   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Student {
	
	private Long id;
	private String stuName;
	private Sex sex;
	private Long selfcardNo;
	private String remark;
	
	private StudentSelfcard studentSelfcard;
	private List<StudentLecture> studentLectureList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Long getSelfcardNo() {
		return selfcardNo;
	}
	public void setSelfcardNo(Long selfcardNo) {
		this.selfcardNo = selfcardNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public StudentSelfcard getStudentSelfcard() {
		return studentSelfcard;
	}
	public void setStudentSelfcard(StudentSelfcard studentSelfcard) {
		this.studentSelfcard = studentSelfcard;
	}
	public List<StudentLecture> getStudentLectureList() {
		return studentLectureList;
	}
	public void setStudentLectureList(List<StudentLecture> studentLectureList) {
		this.studentLectureList = studentLectureList;
	}
	
	
	
}
