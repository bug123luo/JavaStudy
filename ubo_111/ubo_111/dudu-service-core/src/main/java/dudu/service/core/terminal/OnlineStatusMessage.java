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

import dudu.service.core.MessageBean;

public class OnlineStatusMessage extends MessageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3641316309662255950L;
	private String eqId;
	/***
	 * 0x0000 sleep mode, 
	 * "0x04x" realtime mode,
	 * 0xffff heartbeat mode.
	 **************************/
	private int modeMask;
	/***
	 * "on" terminal is online,
	 * "off" terminal is offline,
	 * "alive" terminal active pulse.
	 */
	private String status;
	private String timestamp;
	
	
	public OnlineStatusMessage() {
		super.setType("online-status");
	}

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}
	
	public int getModeMask() {
		return modeMask;
	}

	public void setModeMask(int modeMask) {
		this.modeMask = modeMask;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
