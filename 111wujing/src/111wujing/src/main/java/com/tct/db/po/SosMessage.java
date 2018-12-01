package com.tct.db.po;

import java.util.Date;

public class SosMessage {
    private Integer id;

    private String latitude;

    private String longitude;

    private String gunImei;

    private String gunMac;

    private Date sosTime;

    private Date handleTime;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer exceptionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getGunImei() {
        return gunImei;
    }

    public void setGunImei(String gunImei) {
        this.gunImei = gunImei == null ? null : gunImei.trim();
    }

    public String getGunMac() {
        return gunMac;
    }

    public void setGunMac(String gunMac) {
        this.gunMac = gunMac == null ? null : gunMac.trim();
    }

    public Date getSosTime() {
        return sosTime;
    }

    public void setSosTime(Date sosTime) {
        this.sosTime = sosTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }
}