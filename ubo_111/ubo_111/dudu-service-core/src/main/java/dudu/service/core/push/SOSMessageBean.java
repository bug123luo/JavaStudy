package dudu.service.core.push;

import dudu.service.core.push.bean.LocationBean;

public class SOSMessageBean extends BasicMessageBean{

	private static final long serialVersionUID = -9104862276023885138L;
	private String sensorId;
	private String map;
	private LocationBean loc;	
	
	public SOSMessageBean() {
		super.setType("sos");
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public LocationBean getLoc() {
		return loc;
	}

	public void setLoc(LocationBean loc) {
		this.loc = loc;
	}
}
