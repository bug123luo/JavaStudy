package com.tct.db.po;

import java.util.Date;

public class AppGun {
    private Integer id;

    private Integer appId;

    private String gunId;

    private Date createTime;

    private Integer allotState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getGunId() {
        return gunId;
    }

    public void setGunId(String gunId) {
        this.gunId = gunId == null ? null : gunId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAllotState() {
        return allotState;
    }

    public void setAllotState(Integer allotState) {
        this.allotState = allotState;
    }
}