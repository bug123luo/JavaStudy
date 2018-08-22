package dudu.service.core.push;

public class SubscribeMessageBean extends BasicMessageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -638687787410801794L;
	
	private String subscribeSensorId;
	private long fromUserId;
	private String fromUserMobile;
	private String t;
	
	
	public SubscribeMessageBean() {
		this.type = "subscribe";
	}

	public String getSubscribeSensorId() {
		return subscribeSensorId;
	}

	public void setSubscribeSensorId(String subscribeSensorId) {
		this.subscribeSensorId = subscribeSensorId;
	}

	public long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserMobile() {
		return fromUserMobile;
	}
	public void setFromUserMobile(String fromUserMobile) {
		this.fromUserMobile = fromUserMobile;
	}
	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}
	
}
