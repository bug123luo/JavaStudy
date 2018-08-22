/**
 *
 */
package com.ubo.common.terminal;

import java.util.Date;

import dudu.service.core.terminal.BasicMessage;
import dudu.service.core.terminal.TerMinalBasicMessage;

/**
 * @author mrgdren
 */
public class UboSOSMessage extends TerMinalBasicMessage {

    /**
     *
     */
    private static final long serialVersionUID = -4595787321873944349L;
    private String waitUse;
    private String gunNo;
    private String longitude;
    private String latitude;
    private String addressNo;
    private String token;

    public UboSOSMessage() {

    }

    public String getWaitUse() {
        return waitUse;
    }

    public void setWaitUse(String waitUse) {
        this.waitUse = waitUse;
    }

    public String getGunNo() {
        return gunNo;
    }

    public void setGunNo(String gunNo) {
        this.gunNo = gunNo;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
