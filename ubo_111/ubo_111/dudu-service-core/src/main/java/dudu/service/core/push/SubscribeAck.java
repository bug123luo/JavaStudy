package dudu.service.core.push;

public class SubscribeAck extends BasicMessageBean {

	private static final long serialVersionUID = -3173447503931905679L;
	private long fromUserId;
	private String t;
	
	public SubscribeAck() {
		this.type = "auth-ack";
	}

	public long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}
	
}
