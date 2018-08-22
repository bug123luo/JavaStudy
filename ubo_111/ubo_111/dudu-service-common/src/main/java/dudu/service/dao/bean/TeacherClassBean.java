package dudu.service.dao.bean;

import dudu.service.dao.bean.common.ComBean;

import java.sql.Timestamp;

/**
 * @author 杨清辉
 * @version V1.0
 * @Title: 学生考勤
 * @Description: sensorcheck
 * @date 2017-01-03 15:15:53
 */
@SuppressWarnings("serial")
public class TeacherClassBean extends ComBean {
    /**
     * id
     */
    private java.lang.Integer id;
    /**
     * 教师所在学校id
     */
    private java.lang.Integer schoolId;
    /**
     * 教师所在年级id
     */
    private java.lang.Integer gradeId;
    /**
     * 教师手机号
     */
    private java.lang.Long teacherId;
    /**
     * 教师所在班级id
     */
    private java.lang.Integer classId;
    /**
     * 课程id
     */
    private java.lang.Integer courseId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
