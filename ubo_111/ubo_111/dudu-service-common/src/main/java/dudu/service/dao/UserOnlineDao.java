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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dudu.service.dao.bean.UserOnlineBean;


public class UserOnlineDao extends DuduDaoSupport {

	public UserOnlineDao() {
		
	}
	
	public void save(UserOnlineBean onlineBean) throws DaoException {
		super.save(onlineBean);
	}
	
	public void update(long userId, Boolean online) throws DaoException {
		
		String hql = String.format(
			"UPDATE UserOnlineBean uob SET " + 
			"uob.online=%b," + 
			"uob.chgTime=%d " + 
			"WHERE uob.userId=%d " , 
			online,
			Calendar.getInstance().getTime().getTime() / 1000,
			userId);
		
		super.update(hql);
	}
	
	public UserOnlineBean get(long userId) throws DaoException {
		
		return super.get(UserOnlineBean.class, userId);
	}
	
	public List<UserOnlineBean> get(List<Long> userIds) throws DaoException {
		
		StringBuilder sensorIdCollection = new StringBuilder();
		sensorIdCollection.append("(");
		for (Long id : userIds) {
			sensorIdCollection.append(id);
		}
		sensorIdCollection.deleteCharAt(sensorIdCollection.lastIndexOf(","));
		sensorIdCollection.append(")");
		
		String hql = String.format(
			"FROM UserOnlineBean uob WHERE uob.sensorId in %s",
			sensorIdCollection.toString());
		
		return super.query(hql);
	} 
	
	
	// 获取App用户每天在线数
	public long getOnlineCount() throws DaoException, ParseException {

		String hql = String.format(
			"SELECT count(*) FROM UserOnlineBean uob " + 
			"WHERE uob.chgTime>%d ",
			getZeroDate().getTime()/1000);
		
		return  (long) super.uniqueResult(hql);
	}
	
	// 获取App用户每天激活数
	public long getActivateCount() throws DaoException, ParseException {
		String hql = String.format(
			"SELECT count(*) FROM UserOnlineBean uob " + 
			"WHERE uob.crtTime>%d ",
			getZeroDate().getTime()/1000);
		
		return  (long) super.uniqueResult(hql);
	}
	
	
	private Date getZeroDate() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		String date = String.format("%d-%d-%d %s", year, month, day, " 00:00:00");
		
		return sdf.parse(date);
	}
	
}
