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
package dudu.service.core.terminal;

public class HeartbeatMessage extends BasicMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7156689420333394134L;
	
	private String sessionToken;
	private int eQuantity;
	private short gpsStatus;
	
	public HeartbeatMessage() {
		
	}
	
	public int geteQuantity() {
		return eQuantity;
	}

	public void seteQuantity(int eQuantity) {
		this.eQuantity = eQuantity;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public short getGpsStatus() {
		return gpsStatus;
	}

	public void setGpsStatus(short gpsStatus) {
		this.gpsStatus = gpsStatus;
	}

}
