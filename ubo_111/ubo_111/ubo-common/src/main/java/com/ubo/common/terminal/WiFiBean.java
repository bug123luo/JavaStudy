package com.ubo.common.terminal;

import java.io.Serializable;

public class WiFiBean implements Serializable {

	private static final long serialVersionUID = 5481832927547965886L;

	// {"macaddress":"00:0b:0e:7d:17:82","time":"0","singalstrength":"-8"},
	private String mac;
	private String time;
	private String singalstrength;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSingalstrength() {
		return singalstrength;
	}

	public void setSingalstrength(String singalstrength) {
		this.singalstrength = singalstrength;
	}

}
