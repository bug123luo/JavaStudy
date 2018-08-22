package dudu.service.core.push;

public class LowbatMessageBean extends BasicMessageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1816658933993021901L;
	private String sensorId;
	
	public LowbatMessageBean() {
		super.setType("lowbat");
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
}
