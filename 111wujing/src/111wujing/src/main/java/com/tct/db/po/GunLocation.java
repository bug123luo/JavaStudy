package com.tct.db.po;

import java.util.Date;

public class GunLocation {
    private Long id;

    private Integer appId;

    private String gunId;

    private String gunMac;

    private String latitude;

    private String longitude;

    private Date createTime;

    private Date updateTime;

    private String areaCode;

    private String speed;

    private String director;

    private String gunDeviceBatteryPower;

    private Integer gunDeviceState;

    private String imei;

    private Integer imeiType;

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

    public String getGunId() {
        return gunId;
    }

    public void setGunId(String gunId) {
        this.gunId = gunId == null ? null : gunId.trim();
    }

    public String getGunMac() {
        return gunMac;
    }

    public void setGunMac(String gunMac) {
        this.gunMac = gunMac == null ? null : gunMac.trim();
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed == null ? null : speed.trim();
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director == null ? null : director.trim();
    }

    public String getGunDeviceBatteryPower() {
        return gunDeviceBatteryPower;
    }

    public void setGunDeviceBatteryPower(String gunDeviceBatteryPower) {
        this.gunDeviceBatteryPower = gunDeviceBatteryPower == null ? null : gunDeviceBatteryPower.trim();
    }

    public Integer getGunDeviceState() {
        return gunDeviceState;
    }

    public void setGunDeviceState(Integer gunDeviceState) {
        this.gunDeviceState = gunDeviceState;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Integer getImeiType() {
        return imeiType;
    }

    public void setImeiType(Integer imeiType) {
        this.imeiType = imeiType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}