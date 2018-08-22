package com.ubo.common.terminal;

import dudu.service.core.terminal.TerMinalBasicMessage;


public class SOSRequestMessage extends TerMinalBasicMessage {

    /**
     *
     */
    private static final long serialVersionUID = -6875108799101150277L;

    private String reserve;
    private String bluetoothMac;
    private String lo;
    private String la;
    private String areaCode;
    private String authCode;

    public SOSRequestMessage() {

    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getBluetoothMac() {
        return bluetoothMac;
    }

    public void setBluetoothMac(String bluetoothMac) {
        this.bluetoothMac = bluetoothMac;
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

    public void setLa(String la) {
        this.la = la;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
