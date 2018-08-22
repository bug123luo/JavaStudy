package dudu.service.dao.bean.info;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/4.
 */
public class StudentCheckInfo implements Serializable {

    private static final long serialVersionUID = 5456096809813268329L;

    private String sensorId;
    private String name;
    private String avatar;
    private String checkStatus;
    private Long intoTime;
    private Long outTime;

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Long getIntoTime() {
        return intoTime;
    }

    public void setIntoTime(Long intoTime) {
        this.intoTime = intoTime;
    }

    public Long getOutTime() {
        return outTime;
    }

    public void setOutTime(Long outTime) {
        this.outTime = outTime;
    }
}
