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

public class SOSBean implements Serializable, Comparable<SOSBean> {

	private static final long serialVersionUID = -2268970655345162824L;
	private String sosId;
	private String sensorId;
	private Long crtTime;
	private float lo;
	private float la;
	private int type;
	
	public SOSBean() {
		
	}


	public String getSosId() {
		return sosId;
	}

	public void setSosId(String sosId) {
		this.sosId = sosId;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public Long getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Long crtTime) {
		this.crtTime = crtTime;
	}

	public float getLo() {
		return lo;
	}

	public void setLo(float lo) {
		this.lo = lo;
	}

	public float getLa() {
		return la;
	}

	public void setLa(float la) {
		this.la = la;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int compareTo(SOSBean o) {
		Long diff = this.crtTime - o.crtTime;
		if (diff > 0) {
			return -1;
		} else if (diff < 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
