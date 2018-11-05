package com.tct.db.po;

import java.util.Date;

public class AppGunUser {
    private Integer id;

    private Integer appId;

    private Integer gunUserId;

    private Date createTime;

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

    public Integer getGunUserId() {
        return gunUserId;
    }

    public void setGunUserId(Integer gunUserId) {
        this.gunUserId = gunUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}