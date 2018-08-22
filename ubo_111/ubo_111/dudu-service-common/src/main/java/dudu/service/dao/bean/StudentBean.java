package dudu.service.dao.bean;

import dudu.service.dao.bean.common.ComBean;

import java.sql.Timestamp;

/**
 * @author 杨清辉
 * @version V1.0
 * @Title: 学生
 * @Description: sensorcheck
 * @date 2017-01-03 15:15:53
 */
@SuppressWarnings("serial")
public class StudentBean extends ComBean {
    /**id*/
    private java.lang.Integer id;
    /**班级*/
    private java.lang.Integer classId;
    /**友娃卡*/
    private java.lang.String sensorId;
    /**学生证号*/
    private java.lang.String studentNum;
    /**学生姓名*/
    private java.lang.String name;
    /**学生性别*/
    private java.lang.String sex;
    /**生日*/
    private Timestamp birthday;
    /**昵称*/
    private java.lang.String nick;
    /**头像*/
    private java.lang.String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
