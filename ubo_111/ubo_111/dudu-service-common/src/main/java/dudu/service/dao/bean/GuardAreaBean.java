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

public class GuardAreaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7518207286564886972L;
	private long gdId;
	private String sensorId;
	private String title;
	private float longitude;
	private float latitude;
	private int radius;
	private String type;
	private String startCron;
	private String endCron;
	private byte flag;
	private long crtTime;
	private long chgTime;
	private int trueId;
	private int inOut;
	private long inOutTime;
	
	public GuardAreaBean() {
		
	}

	public long getGdId() {
		return gdId;
	}

	public void setGdId(long gdId) {
		this.gdId = gdId;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartCron() {
		return startCron;
	}

	public void setStartCron(String startCron) {
		this.startCron = startCron;
	}

	public String getEndCron() {
		return endCron;
	}

	public void setEndCron(String endCron) {
		this.endCron = endCron;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
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

	public int getTrueId() {
		return trueId;
	}

	public void setTrueId(int trueId) {
		this.trueId = trueId;
	}

	public int getInOut() {
		return inOut;
	}

	public void setInOut(int inOut) {
		this.inOut = inOut;
	}

	public long getInOutTime() {
		return inOutTime;
	}

	public void setInOutTime(long inOutTime) {
		this.inOutTime = inOutTime;
	}
	
}
