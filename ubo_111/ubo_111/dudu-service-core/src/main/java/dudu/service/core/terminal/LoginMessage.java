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

public class LoginMessage extends BasicMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 743247531812082727L;
	
	private String protocolVer;
	private byte mode;
	
	private String sessionToken;
	
	public LoginMessage() {
		
	}

	public String getProtocolVer() {
		return protocolVer;
	}
	
	public void setProtocolVer(String protocolVer) {
		this.protocolVer = protocolVer;
	}

	public byte getMode() {
		return mode;
	}

	public void setMode(byte mode) {
		this.mode = mode;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
}
