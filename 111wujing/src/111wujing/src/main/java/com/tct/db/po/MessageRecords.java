package com.tct.db.po;

public class MessageRecords {
    private Integer id;

    private String serlNum;

    private String message;

    private String gunId;

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

    public String getGunId() {
        return gunId;
    }

    public void setGunId(String gunId) {
        this.gunId = gunId == null ? null : gunId.trim();
    }
}