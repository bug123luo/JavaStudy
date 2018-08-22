package dudu.service.core.push;

public class SpaceStatusMessageBean extends BasicMessageBean {


	private static final long serialVersionUID = -5560052419461555395L;
	private String sensorId;
	private String status; /*0:full 1:empty*/
	private String t;
	
	public SpaceStatusMessageBean() {
		super.setType("space-status");
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}
	
}
