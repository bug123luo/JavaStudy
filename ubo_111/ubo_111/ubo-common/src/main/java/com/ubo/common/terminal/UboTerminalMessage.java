package com.ubo.common.terminal;

import java.io.Serializable;

public class UboTerminalMessage implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6623607669077505438L;

    private String tid; //唯一标识
    private String deviceVersion;//
    private String deviceType;//设备类型
    private String serial;//交易流水号
    private String cmd;//报文类型
    private String body;//报文体
    private String masssgeTime;//报文体净长度
    private String sessionToken;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMasssgeTime() {
        return masssgeTime;
    }

    public void setMasssgeTime(String masssgeTime) {
        this.masssgeTime = masssgeTime;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
