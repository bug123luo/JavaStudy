package com.tct.db.po;

public class MessageRecords {
    private Integer id;

    private String serlNum;

    private String message;

    private String gunImei;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerlNum() {
        return serlNum;
    }

    public void setSerlNum(String serlNum) {
        this.serlNum = serlNum == null ? null : serlNum.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getGunImei() {
        return gunImei;
    }

    public void setGunImei(String gunImei) {
        this.gunImei = gunImei == null ? null : gunImei.trim();
    }
}