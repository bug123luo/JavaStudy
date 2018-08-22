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

package dudu.service.dao;

import java.util.List;

import dudu.service.dao.bean.SensorConfigurationBean;
import dudu.service.dto.Page;

public class SensorConfigurationDao extends DuduDaoSupport {

	public SensorConfigurationDao() {
		
	}
	
	public void save(SensorConfigurationBean scBean) throws DaoException {
		super.save(scBean);
	}
	
	public SensorConfigurationBean get(String id) throws DaoException {
		return super.get(SensorConfigurationBean.class, id);
	}
	
	public void updateDormancyTime(String sensorId, String dTime) throws DaoException {
		String hql = String.format(
				"UPDATE SensorConfigurationBean scb SET " + 
				"scb.dormancyTime='%s' " + 
				"WHERE scb.sensorId='%s'", 
				dTime,
				sensorId);
			
		super.update(hql);
	}
	
	public void updateImsiAndIccid(String sensorId, String imsi, String iccid) throws DaoException {
		String hql = String.format(
			"UPDATE SensorConfigurationBean scb SET " + 
				"scb.imsi='%s', " +
				"scb.iccid='%s' " +
				"WHERE scb.sensorId='%s'", 
				imsi,
				iccid,
				sensorId);
		
		super.update(hql);
	}
	
	public void updateLocationPatternFrequency(String sensorId, int pt, int fre) throws DaoException {
		String hql = String.format(
				"UPDATE SensorConfigurationBean scb SET " + 
				"scb.locationPattern=%d " + 
				"scb.locationFrequency=%d " + 		
				"WHERE scb.sensorId='%s'", 
				pt,
				fre,
				sensorId);
			
		super.update(hql);	
	}
	
	public void updateRegionAlarm(String sensorId, String ra) throws DaoException {
		String hql = String.format(
				"UPDATE SensorConfigurationBean scb SET " + 
				"scb.regionAlarm='%s' " + 
				"WHERE scb.sensorId='%s'", 
				ra,
				sensorId);
			
		super.update(hql);	
	}
	
	public void updateHeartbeatFrequency(String sensorId, int hf) throws DaoException {
		String hql = String.format(
				"UPDATE SensorConfigurationBean scb SET " + 
				"scb.heartbeatFrequency=%d " + 
				"WHERE scb.sensorId='%s'", 
				hf,
				sensorId);
			
		super.update(hql);
	}
	
	public void updatePositionTimeInterval(String sensorId, String positionTimeInterval) throws DaoException {
		String hql = String.format(
				"UPDATE SensorConfigurationBean scb SET " + 
				"scb.positionTimeInterval='%s' " + 
				"WHERE scb.sensorId='%s'", 
				positionTimeInterval,
				sensorId);
			
		super.update(hql);
	}
	
	
	public void updateFamilyNumbers(String sensorId, String familyNumbers) throws DaoException {
		
		String hql = String.format(
			"UPDATE SensorConfigurationBean scb SET " + 
				"scb.familyNumber='%s' " + 
				"WHERE scb.sensorId='%s'", 
				familyNumbers,
				sensorId);
		
		super.update(hql);
	}
	
	public void updateChatSwitch(String sensorId, int cSwitch) throws DaoException {
			
		String hql = String.format(
			"UPDATE SensorConfigurationBean scb SET " + 
				"scb.chatSwitch=%d " + 
				"WHERE scb.sensorId='%s'", 
				cSwitch,
				sensorId);
		
		super.update(hql);
	}
	
	public void getSensorList(List<SensorConfigurationBean> beans,Page page) throws DaoException{
		super.find(" from SensorConfigurationBean e ", beans, page);
	}
}
