/*
 * Copyright 2015 The Dudu Project
 *
 * The Dudu Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package dudu.service.dao.bean;

import java.io.Serializable;

public class SensorConfigurationBean implements Serializable {

	private static final long serialVersionUID = -6586745734115644884L;

	private String sensorId;
	private String ipaddressPort;
	private String familyNumber;
	private int locationFrequency;
	private int locationPattern;
	private String dormancyTime;
	private String classesPattern;
	private int littleRedFlowers;
	private String regionAlarm;
	private int bluetoothOnOff;
	private int phoneRing;
	private int soundVolume; // 通话音量
	private int callVolume;  // 来电音量
	private String registerNumber;
	private int heartbeatFrequency;
	private int soundRecordTime;
	private int crtTime;
	private String imsi;
	private String iccid;
	private String positionTimeInterval;
	private int chatSwitch;

	public SensorConfigurationBean() {

	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getIpaddressPort() {
		return ipaddressPort;
	}

	public void setIpaddressPort(String ipaddressPort) {
		this.ipaddressPort = ipaddressPort;
	}

	public String getFamilyNumber() {
		return familyNumber;
	}

	public void setFamilyNumber(String familyNumber) {
		this.familyNumber = familyNumber;
	}

	public int getLocationFrequency() {
		return locationFrequency;
	}

	public void setLocationFrequency(int locationFrequency) {
		this.locationFrequency = locationFrequency;
	}

	public int getLocationPattern() {
		return locationPattern;
	}

	public void setLocationPattern(int locationPattern) {
		this.locationPattern = locationPattern;
	}

	public String getDormancyTime() {
		return dormancyTime;
	}

	public void setDormancyTime(String dormancyTime) {
		this.dormancyTime = dormancyTime;
	}

	public String getClassesPattern() {
		return classesPattern;
	}

	public void setClassesPattern(String classesPattern) {
		this.classesPattern = classesPattern;
	}

	public int getLittleRedFlowers() {
		return littleRedFlowers;
	}

	public void setLittleRedFlowers(int littleRedFlowers) {
		this.littleRedFlowers = littleRedFlowers;
	}

	public String getRegionAlarm() {
		return regionAlarm;
	}

	public void setRegionAlarm(String regionAlarm) {
		this.regionAlarm = regionAlarm;
	}

	public int getBluetoothOnOff() {
		return bluetoothOnOff;
	}

	public void setBluetoothOnOff(int bluetoothOnOff) {
		this.bluetoothOnOff = bluetoothOnOff;
	}

	public int getPhoneRing() {
		return phoneRing;
	}

	public void setPhoneRing(int phoneRing) {
		this.phoneRing = phoneRing;
	}

	public int getSoundVolume() {
		return soundVolume;
	}

	public void setSoundVolume(int soundVolume) {
		this.soundVolume = soundVolume;
	}

	public int getCallVolume() {
		return callVolume;
	}

	public void setCallVolume(int callVolume) {
		this.callVolume = callVolume;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public int getHeartbeatFrequency() {
		return heartbeatFrequency;
	}

	public void setHeartbeatFrequency(int heartbeatFrequency) {
		this.heartbeatFrequency = heartbeatFrequency;
	}

	public int getSoundRecordTime() {
		return soundRecordTime;
	}

	public void setSoundRecordTime(int soundRecordTime) {
		this.soundRecordTime = soundRecordTime;
	}

	public int getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(int crtTime) {
		this.crtTime = crtTime;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getPositionTimeInterval() {
		return positionTimeInterval;
	}

	public void setPositionTimeInterval(String positionTimeInterval) {
		this.positionTimeInterval = positionTimeInterval;
	}

	public int getChatSwitch() {
		return chatSwitch;
	}

	public void setChatSwitch(int chatSwitch) {
		this.chatSwitch = chatSwitch;
	}
}
