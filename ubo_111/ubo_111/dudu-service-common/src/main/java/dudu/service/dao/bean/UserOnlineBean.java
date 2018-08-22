package dudu.service.dao.bean;

import java.io.Serializable;

public class UserOnlineBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7593062336679098461L;
	private Long userId;
	private Boolean online;
	/*UTC seconds*/
	private Long crtTime;
	/*UTC seconds*/
	private Long chgTime;
	
	
	public UserOnlineBean() {
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Long getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Long crtTime) {
		this.crtTime = crtTime;
	}

	public Long getChgTime() {
		return chgTime;
	}

	public void setChgTime(Long chgTime) {
		this.chgTime = chgTime;
	}
	
}
