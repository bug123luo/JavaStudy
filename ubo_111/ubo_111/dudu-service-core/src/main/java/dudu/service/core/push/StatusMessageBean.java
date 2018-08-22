package dudu.service.core.push;

public class StatusMessageBean extends BasicMessageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4261157524677378056L;
	private String sensorId;
	private String online; /*true: online, false: offline*/
	private String t;
	
	public StatusMessageBean() {
		super.setType("status");
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}
	
}
