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
public class ClassBean extends ComBean {
    /**id*/
    private java.lang.Integer id;
    /**班级名称*/
    private java.lang.String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
