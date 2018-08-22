package dudu.service.core.push;

public class FollowMessageBean extends BasicMessageBean {

	private static final long serialVersionUID = 1L;
	private String sensorId;
	private String isFollow;
	private String t;

	public FollowMessageBean() {
		super.setType("follow");
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(String isFollow) {
		this.isFollow = isFollow;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

}
