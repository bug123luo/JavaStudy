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
public class TeacherBean extends ComBean {
    /**
     * id
     */
    private Integer id;
    /**
     * 教师手机号
     */
    private String phone;
    /**
     * 教师登录密码
     */
    private String password;
    /**
     * 教师所在学校id
     */
    private Integer schoolId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private Timestamp birthday;
    /**
     * 头像
     */
    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
