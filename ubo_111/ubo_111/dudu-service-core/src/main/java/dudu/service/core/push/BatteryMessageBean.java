package dudu.service.core.push;

public class BatteryMessageBean extends BasicMessageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7641956972732933645L;
	private String sensorId;
	private String bat;
	private String t;
	
	public BatteryMessageBean() {
		super.setType("battery");
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getBat() {
		return bat;
	}

	public void setBat(String bat) {
		this.bat = bat;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}
}
