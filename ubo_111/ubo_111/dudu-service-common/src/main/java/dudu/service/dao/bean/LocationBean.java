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

public class LocationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7828447253548227315L;
	private String locId;
	private float lo;
	private float la;
	private int an;
	private float sp;
	private long t;
	
	public LocationBean() {
		
	}

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
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

	public int getAn() {
		return an;
	}

	public void setAn(int an) {
		this.an = an;
	}

	public float getSp() {
		return sp;
	}

	public void setSp(float sp) {
		this.sp = sp;
	}

	public long getT() {
		return t;
	}

	public void setT(long t) {
		this.t = t;
	}
	
	
}
