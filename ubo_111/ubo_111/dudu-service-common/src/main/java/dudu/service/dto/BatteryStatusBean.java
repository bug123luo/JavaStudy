/**
 * 用于缓存设备电量信息的传输对象
 * @author danyuan
 * @date 2016-07-20
 */
package dudu.service.dto;

import java.io.Serializable;

public class BatteryStatusBean implements Serializable{
	private String sensorId;//设备IMEI号
	private String bat;//电量
	private String t;//信息获取时间
	private String msgId;//消息id
	private String userId;//用户id
	public String getSensorId() {
		return sensorId;
	}
	public BatteryStatusBean setSensorId(String sensorId) {
		this.sensorId = sensorId;
		return this;
	}
	public String getBat() {
		return bat;
	}
	public BatteryStatusBean setBat(String bat) {
		this.bat = bat;
		return this;
	}
	public String getT() {
		return t;
	}
	public BatteryStatusBean setT(String t) {
		this.t = t;
		return this;
	}
	public String getMsgId() {
		return msgId;
	}
	public BatteryStatusBean setMsgId(String msgId) {
		this.msgId = msgId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public BatteryStatusBean setUserId(String userId) {
		this.userId = userId;
		return this;
	}
}
