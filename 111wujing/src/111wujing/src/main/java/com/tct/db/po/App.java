package com.tct.db.po;

public class App {
    private Integer id;

    private String appSystem;

    private String appSystemVersion;

    private String appPhoneTypeName;

    private String appName;

    private String appMac;

    private String appImei;

    private String appPhone;

    private String appReadableCode;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppSystem() {
        return appSystem;
    }

    public void setAppSystem(String appSystem) {
        this.appSystem = appSystem == null ? null : appSystem.trim();
    }

    public String getAppSystemVersion() {
        return appSystemVersion;
    }

    public void setAppSystemVersion(String appSystemVersion) {
        this.appSystemVersion = appSystemVersion == null ? null : appSystemVersion.trim();
    }

    public String getAppPhoneTypeName() {
        return appPhoneTypeName;
    }

    public void setAppPhoneTypeName(String appPhoneTypeName) {
        this.appPhoneTypeName = appPhoneTypeName == null ? null : appPhoneTypeName.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppMac() {
        return appMac;
    }

    public void setAppMac(String appMac) {
        this.appMac = appMac == null ? null : appMac.trim();
    }

    public String getAppImei() {
        return appImei;
    }

    public void setAppImei(String appImei) {
        this.appImei = appImei == null ? null : appImei.trim();
    }

    public String getAppPhone() {
        return appPhone;
    }

    public void setAppPhone(String appPhone) {
        this.appPhone = appPhone == null ? null : appPhone.trim();
    }

    public String getAppReadableCode() {
        return appReadableCode;
    }

    public void setAppReadableCode(String appReadableCode) {
        this.appReadableCode = appReadableCode == null ? null : appReadableCode.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}