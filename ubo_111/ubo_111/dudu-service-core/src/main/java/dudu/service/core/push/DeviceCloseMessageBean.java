package dudu.service.core.push;

public class DeviceCloseMessageBean extends BasicMessageBean {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1795947370708102218L;
	private String sensorId;
	private String type; /*0: 自动关机, 1: 手动关机*/
	private String t;
	
	public DeviceCloseMessageBean() {
		super.setType("d-close");
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}
	
}
