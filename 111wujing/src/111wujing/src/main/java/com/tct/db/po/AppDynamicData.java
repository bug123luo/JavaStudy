package com.tct.db.po;

import java.util.Date;

public class AppDynamicData {
    private Long id;

    private Integer appId;

    private String appBatteryPower;

    private Date createTime;

    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppBatteryPower() {
        return appBatteryPower;
    }

    public void setAppBatteryPower(String appBatteryPower) {
        this.appBatteryPower = appBatteryPower == null ? null : appBatteryPower.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}