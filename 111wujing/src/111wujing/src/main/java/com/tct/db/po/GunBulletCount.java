package com.tct.db.po;

import java.util.Date;

public class GunBulletCount {
    private Integer id;

    private String gunId;

    private Integer bulletNumber;

    private Date createTime;

    private String lo;

    private String la;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGunId() {
        return gunId;
    }

    public void setGunId(String gunId) {
        this.gunId = gunId == null ? null : gunId.trim();
    }

    public Integer getBulletNumber() {
        return bulletNumber;
    }

    public void setBulletNumber(Integer bulletNumber) {
        this.bulletNumber = bulletNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo == null ? null : lo.trim();
    }

    public String getLa() {
        return la;
    }

    public void setLa(String la) {
        this.la = la == null ? null : la.trim();
    }
}