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
public class SensorCheckBean extends ComBean {

    private int id;
    /**
     * 友娃卡
     */
    private String sensorId;
    /**
     * 班级
     */
    private String classId;
    /**
     * 围栏id
     */
    private Long gradeId;
    /**
     * 考勤日期yyyy-MM-dd
     */
    private long checkDate;
    /**
     * 入围栏时间HH:mm:ss
     */
    private long intoTime;
    /**
     * 进入状态
     */
    private String intoStatus;
    /**
     * 出围栏时间
     */
    private long outTime;
    /**
     * 出围栏状态
     */
    private String outStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public long getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(long checkDate) {
        this.checkDate = checkDate;
    }

    public long getIntoTime() {
        return intoTime;
    }

    public void setIntoTime(long intoTime) {
        this.intoTime = intoTime;
    }

    public String getIntoStatus() {
        return intoStatus;
    }

    public void setIntoStatus(String intoStatus) {
        this.intoStatus = intoStatus;
    }

    public long getOutTime() {
        return outTime;
    }

    public void setOutTime(long outTime) {
        this.outTime = outTime;
    }

    public String getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(String outStatus) {
        this.outStatus = outStatus;
    }
}
