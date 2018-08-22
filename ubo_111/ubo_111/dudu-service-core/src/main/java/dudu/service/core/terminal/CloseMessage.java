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

public class CloseMessage extends BasicMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1462147844199004795L;
	
	private String sessionToken;
	/***
	 * exitCode
	 * 0 connection is closed by accident, such as network drop;
	 * 1 connection is closed by equipment which has sent a logout message to server;
	 * 2 connection is closed by server, in the case server shutdown;
	 * 3 connection is closed by accident, in the case server crash.
	 * 4 connection is closed when a new login happens.
	 * 5 connection is closed by exception.
	 * */
	private byte exitCode;
	
	public CloseMessage() {
		
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public byte getExitCode() {
		return exitCode;
	}

	public void setExitCode(byte exitCode) {
		this.exitCode = exitCode;
	}
	
}
