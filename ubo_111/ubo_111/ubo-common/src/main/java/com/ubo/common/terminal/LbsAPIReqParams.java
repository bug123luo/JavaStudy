package com.ubo.common.terminal;

import java.util.List;

public class LbsAPIReqParams {
	
	private List<BaseStationBean> celltowers;
	private List<WiFiBean> wifilist;
	private String mnctype;

	public LbsAPIReqParams() {
		this.mnctype = "gsm";
	}

	public List<BaseStationBean> getCelltowers() {
		return celltowers;
	}

	public void setCelltowers(List<BaseStationBean> celltowers) {
		this.celltowers = celltowers;
	}

	public List<WiFiBean> getWifilist() {
		return wifilist;
	}

	public void setWifilist(List<WiFiBean> wifilist) {
		this.wifilist = wifilist;
	}

	public String getMnctype() {
		return mnctype;
	}

	public void setMnctype(String mnctype) {
		this.mnctype = mnctype;
	}

}
