package com.ubo.common.terminal;

import java.util.Date;

import dudu.service.core.terminal.BasicMessage;

public class UboBatteryMessage extends BasicMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6405233445603634101L;
	private String bat;
	private Date t;
	
	public UboBatteryMessage() {
		
	}

	public String getBat() {
		return bat;
	}

	public void setBat(String bat) {
		this.bat = bat;
	}

	public Date getT() {
		return t;
	}

	public void setT(Date t) {
		this.t = t;
	}
}
