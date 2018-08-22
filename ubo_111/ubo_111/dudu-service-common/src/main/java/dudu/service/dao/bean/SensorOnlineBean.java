package dudu.service.dao.bean;

import java.io.Serializable;

public class SensorOnlineBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4522584251810197215L;
	private String sensorId;
	private Boolean online;
	private Boolean position;
	/*UTC seconds*/
	private Long crtTime;
	/*UTC seconds*/
	private Long chgTime;
	
	public SensorOnlineBean() {
		
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Boolean getPosition() {
		return position;
	}

	public void setPosition(Boolean position) {
		this.position = position;
	}

	public Long getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Long crtTime) {
		this.crtTime = crtTime;
	}

	public Long getChgTime() {
		return chgTime;
	}

	public void setChgTime(Long chgTime) {
		this.chgTime = chgTime;
	}
	
}
