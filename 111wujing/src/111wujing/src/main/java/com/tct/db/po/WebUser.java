package com.tct.db.po;

import java.util.Date;

public class WebUser {
    private Integer id;

    private String name;

    private String webUserName;

    private String webUserPassword;

    private String webUserPhone;

    private Integer departmentId;

    private Boolean isDel;

    private Boolean isJob;

    private Integer uid;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getWebUserName() {
        return webUserName;
    }

    public void setWebUserName(String webUserName) {
        this.webUserName = webUserName == null ? null : webUserName.trim();
    }

    public String getWebUserPassword() {
        return webUserPassword;
    }

    public void setWebUserPassword(String webUserPassword) {
        this.webUserPassword = webUserPassword == null ? null : webUserPassword.trim();
    }

    public String getWebUserPhone() {
        return webUserPhone;
    }

    public void setWebUserPhone(String webUserPhone) {
        this.webUserPhone = webUserPhone == null ? null : webUserPhone.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Boolean getIsJob() {
        return isJob;
    }

    public void setIsJob(Boolean isJob) {
        this.isJob = isJob;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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
}