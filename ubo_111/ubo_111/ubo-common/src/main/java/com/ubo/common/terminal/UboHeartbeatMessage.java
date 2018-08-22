package com.ubo.common.terminal;


import dudu.service.core.terminal.TerMinalBasicMessage;

public class UboHeartbeatMessage extends TerMinalBasicMessage {

	private static final long serialVersionUID = 8824328592014536263L;

	private String realTimeState;//随行设备与蓝牙绑定状态 0未绑定枪支id,1绑定枪支ID，2绑定枪支ID已离位告警
	private String state;//出入库状态 0 出库 1入库
	private String bluetoothMac;//枪支id
	private String lo;//经度
	private String la;//纬度
	private String areaCode;//小区代码
	private String batteryPower;//随行设备电量
	private String deviceBatteryPower;//离位置告警设备电量
	private String exception;//异常事件
	private String authCode;//授权码

	public String getRealTimeState() {
		return realTimeState;
	}

	public void setRealTimeState(String realTimeState) {
		this.realTimeState = realTimeState;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBluetoothMac() {
		return bluetoothMac;
	}

	public void setBluetoothMac(String bluetoothMac) {
		this.bluetoothMac = bluetoothMac;
	}

	public String getLo() {
		return lo;
	}

	public void setLo(String lo) {
		this.lo = lo;
	}

	public String getLa() {
		return la;
	}

	public void setLa(String la) {
		this.la = la;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getBatteryPower() {
		return batteryPower;
	}

	public void setBatteryPower(String batteryPower) {
		this.batteryPower = batteryPower;
	}

	public String getDeviceBatteryPower() {
		return deviceBatteryPower;
	}

	public void setDeviceBatteryPower(String deviceBatteryPower) {
		this.deviceBatteryPower = deviceBatteryPower;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
}

