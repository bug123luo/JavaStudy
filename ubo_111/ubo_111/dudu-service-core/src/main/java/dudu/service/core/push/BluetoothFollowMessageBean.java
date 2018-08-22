package dudu.service.core.push;

public class BluetoothFollowMessageBean extends BasicMessageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7641956972732933645L;
	private String flag;
	private String sensorId;
	private String t;
	
	public BluetoothFollowMessageBean() {
		super.setType("BLUETOOTH-FOLLOW");
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	
}
