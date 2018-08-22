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

public class AuthResultMessageBean extends BasicMessageBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static int ERROR_SUCCESS=0;
	public final static int ERROR_SIGNED_FAILED=1;
	public final static int ERROR_NOT_LOGIN=2;
	public final static int ERROR_SLEEP_TIMEOUT=3;
	
	/***
	 * -0 auth success !
	 * -1 signed failed !
	 * -2 the user has not login!
	 * -3 the channel has sleep out of heartbeatTimeout,caused by channel broken!
	 * */
	private int error;
	
	public AuthResultMessageBean() {
		type="authResult";
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}
}
