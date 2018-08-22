package com.ubo.common.terminal;

import java.io.Serializable;

public class BaseStationBean implements Serializable {

	private static final long serialVersionUID = 5481832929944965886L;
	
	// {"cell_id":"26353","lac":"24802","mcc":"460","mnc":"0","signalstrength":"-60"},
	private String cell_id;
	private String lac;
	private String mcc;
	private String mnc;
	private String signalstrength;
	
	public BaseStationBean() {
		this.mcc = "460";
		this.signalstrength = "-70";
	}
	
	public String getCell_id() {
		return cell_id;
	}

	public void setCell_id(String cell_id) {
		this.cell_id = cell_id;
	}

	public String getLac() {
		return lac;
	}

	public void setLac(String lac) {
		this.lac = lac;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public String getMnc() {
		return mnc;
	}

	public String getSignalstrength() {
		return signalstrength;
	}

}
