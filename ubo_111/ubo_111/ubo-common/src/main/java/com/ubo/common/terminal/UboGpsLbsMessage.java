package com.ubo.common.terminal;

import java.util.Date;
import java.util.List;

import dudu.service.core.terminal.BasicMessage;
	
public class UboGpsLbsMessage extends BasicMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4738854377591447002L;
	private boolean isGpsOn;
	
	/***
	 * 0 normal
	 * 1 SOS
	 * 2 active location
	 * 3 heartbeat
	 * */
	private byte fireType;
	
	private float longitude;
	private float latitude;
	private Date t;
	
	private float voltage;
	private int eQuantity;
	
	private short mcc;
	private short mnc;
	
	// base station info
	private List<LbsBean> lbs;
	
	// wifi info
	private List<WiFiBean> wifi;
	
	public UboGpsLbsMessage() {
		this.mcc = 460; /*China Country Code*/
	}
	
	public boolean isGpsOn() {
		return isGpsOn;
	}

	public void setGpsOn(boolean isGpsOn) {
		this.isGpsOn = isGpsOn;
	}

	public byte getFireType() {
		return fireType;
	}

	public void setFireType(byte fireType) {
		this.fireType = fireType;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public Date getT() {
		return t;
	}

	public void setT(Date t) {
		this.t = t;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

	public int getEQuantity() {
		return eQuantity;
	}
	
	public void setEQuantity(int eQuantity) {
		this.eQuantity = eQuantity;
	}

	public short getMcc() {
		return mcc;
	}
	
	public void setMcc(short mcc) {
		this.mcc = mcc;
	}
	
	public short getMnc() {
		return mnc;
	}
	
	public void setMnc(short mnc) {
		this.mnc = mnc;
	}

	public List<LbsBean> getLbs() {
		return lbs;
	}

	public void setLbs(List<LbsBean> lbs) {
		this.lbs = lbs;
	}

	public List<WiFiBean> getWifi() {
		return wifi;
	}

	public void setWifi(List<WiFiBean> wifi) {
		this.wifi = wifi;
	}
	
}

