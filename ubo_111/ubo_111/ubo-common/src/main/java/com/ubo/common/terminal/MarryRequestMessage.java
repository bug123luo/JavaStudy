package com.ubo.common.terminal;

import dudu.service.core.terminal.TerMinalBasicMessage;


public class MarryRequestMessage extends TerMinalBasicMessage {

    /**
     *
     */
    private static final long serialVersionUID = -6875108799101150277L;

    private String reserve;
    private String gunId;
    private String time;
    private String lo;
    private String lg;
    private String failType;
    private String sessionId;

    public MarryRequestMessage() {

    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getGunId() {
        return gunId;
    }

    public void setGunId(String gunId) {
        this.gunId = gunId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public String getFailType() {
        return failType;
    }

    public void setFailType(String failType) {
        this.failType = failType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
