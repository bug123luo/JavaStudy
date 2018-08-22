package dudu.service.core.push;

import dudu.service.core.push.bean.LocationBean;

public class RecordMessageBean extends BasicMessageBean {

	private static final long serialVersionUID = 1L;

	private long fromUserId;
	private String sensorId;
	private String recordUrl;
	private String subType;
	private int recordTime;
	private String t;
	/**
	 * 录音缓存id
	 */
	private String recordId;
	
	private LocationBean loc;	

	public RecordMessageBean() {
		this.type = "record";
	}

	public long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getRecordUrl() {
		return recordUrl;
	}

	public void setRecordUrl(String recordUrl) {
		this.recordUrl = recordUrl;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	public int getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(int recordTime) {
		this.recordTime = recordTime;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public LocationBean getLoc() {
		return loc;
	}

	public void setLoc(LocationBean loc) {
		this.loc = loc;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
}
