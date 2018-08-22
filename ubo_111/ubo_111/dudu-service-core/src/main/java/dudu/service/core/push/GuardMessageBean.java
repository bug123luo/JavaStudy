package dudu.service.core.push;

import dudu.service.core.push.bean.LocationBean;

public class GuardMessageBean extends BasicMessageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 631245212199435118L;
	private String sensorId;
	private String guardId;
	private String guardType;
	private String map;
	private LocationBean loc;	
	
	public GuardMessageBean() {
		super.setType("guard");
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
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
