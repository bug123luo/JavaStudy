package dudu.service.test.dao.bean;

import java.io.Serializable;
import java.util.Date;

public class TrackBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5032355525780870987L;
	
	private String trackId;
	private long trackDev;
	private double trackLng;
	private double trackLat;
	private float trackSpeed;
	private int trackDirect;
	private Date time;
	

	public TrackBean() {
		
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public long getTrackDev() {
		return trackDev;
	}

	public void setTrackDev(long trackDev) {
		this.trackDev = trackDev;
	}

	public double getTrackLng() {
		return trackLng;
	}

	public void setTrackLng(double trackLng) {
		this.trackLng = trackLng;
	}

	public double getTrackLat() {
		return trackLat;
	}

	public void setTrackLat(double trackLat) {
		this.trackLat = trackLat;
	}

	public float getTrackSpeed() {
		return trackSpeed;
	}

	public void setTrackSpeed(float trackSpeed) {
		this.trackSpeed = trackSpeed;
	}

	public int getTrackDirect() {
		return trackDirect;
	}

	public void setTrackDirect(int trackDirect) {
		this.trackDirect = trackDirect;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
