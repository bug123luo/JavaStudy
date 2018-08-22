package com.ubo.common.terminal;

import dudu.service.core.terminal.TerMinalBasicMessage;


public class BasicResponMessage extends TerMinalBasicMessage {

    /**
     *
     */
    private static final long serialVersionUID = -6875108799101150277L;

    private String deviceName;
    private String token;
    private String lo;
    private String lg;
    private String sessionId;

    public BasicResponMessage() {

    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
