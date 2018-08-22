package dudu.service.dao.bean;

import java.io.Serializable;

public class SensorMessageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String msgId;
	private String serialNumber;
	private String body;
	private boolean send;
	private boolean down;
	private boolean expire;
	private String type;
	private long crtTime;
	private long chgTime;

	public SensorMessageBean() {
		
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isSend() {
		return send;
	}

	public void setSend(boolean send) {
		this.send = send;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isExpire() {
		return expire;
	}

	public void setExpire(boolean expire) {
		this.expire = expire;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(long crtTime) {
		this.crtTime = crtTime;
	}

	public long getChgTime() {
		return chgTime;
	}

	public void setChgTime(long chgTime) {
		this.chgTime = chgTime;
	}

}
