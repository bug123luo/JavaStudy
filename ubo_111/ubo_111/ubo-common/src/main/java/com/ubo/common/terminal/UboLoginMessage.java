package com.ubo.common.terminal;

import dudu.service.core.terminal.TerMinalBasicMessage;


public class UboLoginMessage extends TerMinalBasicMessage {

    /**
     *
     */
    private static final long serialVersionUID = -6875108799101150277L;

    private String deviceName;
    private String token;
    private String lo;
    private String la;

    public UboLoginMessage() {

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

    public String getLa() {
        return la;
    }

    public void setLa(String lg) {
        this.la = lg;
    }
}
