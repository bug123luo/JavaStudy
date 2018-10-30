package com.tct.db.po;

import java.util.Date;

public class WebUserLogin {
    private Integer webUserId;

    private String webIp;

    private Integer state;

    private Date logintime;

    private Date logouttime;

    private Integer version;

    public Integer getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(Integer webUserId) {
        this.webUserId = webUserId;
    }

    public String getWebIp() {
        return webIp;
    }

    public void setWebIp(String webIp) {
        this.webIp = webIp == null ? null : webIp.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Date getLogouttime() {
        return logouttime;
    }

    public void setLogouttime(Date logouttime) {
        this.logouttime = logouttime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}