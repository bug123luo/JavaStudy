package com.ubo.common.terminal;

import java.util.Date;

import dudu.service.core.terminal.BasicMessage;

public class UboGuardAreaIndMessage extends BasicMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5150300606160311952L;
	private String guardId;
	/*value: in or out*/
	private String guardType; 
	private float longitude;
	private float latitude;
	private Date t;
	
	public UboGuardAreaIndMessage() {
		
	}

	public String getGuardId() {
		return guardId;
	}

	public void setGuardId(String guardId) {
		this.guardId = guardId;
	}

	public String getGuardType() {
		return guardType;
	}

	public void setGuardType(String guardType) {
		this.guardType = guardType;
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

}
