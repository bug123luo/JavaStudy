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
package dudu.service.core.push;

public class LogoutNotificationMessageBean extends BasicMessageBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7065173757774269585L;
	/***
	 * -1 user with the same id is in login process...
	 * -2 a new login succeed, the old one will logout.
	 * -3 disconnected by server.
	 * -4 authentication fails.
	 * */
	private int error;
	
	public LogoutNotificationMessageBean() {
		
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}
}
