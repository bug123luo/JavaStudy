package com.ubo.common.terminal;

import java.io.Serializable;
import java.util.List;

import dudu.service.core.push.bean.LocationBean;

public class LbsCacheBean implements Serializable {

	private static final long serialVersionUID = -6421284328986570976L;

	private LocationBean location;
	private List<LbsBean> lbs;
	private List<WiFiBean> wifi;

	public LocationBean getLocation() {
		return location;
	}

	public void setLocation(LocationBean location) {
		this.location = location;
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
	
	
	public boolean isLocationSimilar(List<LbsBean> lbsList, List<WiFiBean> wifiList) {
		
		boolean result = false;
		
		if (this.lbs != null && !this.lbs.isEmpty() && lbsList != null && !lbsList.isEmpty()) {
			for (LbsBean lbs1: this.lbs) {
				for (LbsBean lbs2: lbsList) {
					if (lbs1.equals(lbs2)) {
						result = true;
						break;
					}
				}
				
				if (result) {
					break;
				}
			}
		}
		
		if (!result && this.wifi != null && !this.wifi.isEmpty() && wifiList != null && !wifiList.isEmpty()) {
			for (WiFiBean wf1: this.wifi) {
				for (WiFiBean wf2: wifiList) {
					if (wf1.equals(wf2)) {
						result = true;
						break;
					}
				}
				
				if (result) {
					break;
				}
			}
		}
		
		return result;
	}

}
