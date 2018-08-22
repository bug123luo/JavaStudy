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
package dudu.service.common;

import dudu.service.dao.DaoException;

public interface SubscriptionService {
	
	public Boolean isSuscribed(Long userId, String sensorId) 
		throws BusinessException, DaoException;
	
	public long[] getSubscribedUserId(String sensorId) throws Exception;
	public long[] getOnlineSubscribedUserId(String sensorId) throws Exception;
	
	public String[] getSensorKeeperPhone(String sensorId) throws Exception;
	
	public Boolean isSensorKeeper(Long userId, String sensorId) throws BusinessException, DaoException;

}
