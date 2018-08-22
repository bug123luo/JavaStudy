package dudu.service.dao.bean;

import java.io.Serializable;

public class MessageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2697119974812270927L;
	private String msgId;
	private String body;
	private boolean read;
	private long chgTime;
	private String type;

	public MessageBean() {
		
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public long getChgTime() {
		return chgTime;
	}

	public void setChgTime(long chgTime) {
		this.chgTime = chgTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
