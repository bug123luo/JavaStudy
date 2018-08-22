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

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import dudu.service.dao.DaoException;

public interface UserService {

	public Boolean isUserOnline(Long userId) throws BusinessException, DaoException;
	
	public void setOnline(Long usreId, boolean isOnline) throws BusinessException, DaoException;
	
	public String saveMessage(Long userId, dudu.service.core.MessageBean pushMsg) throws DaoException;
	
	public String saveMessage(Long userId, dudu.service.core.MessageBean pushMsg, String subType) throws DaoException;
	
	public String saveMessage(String sensorId, dudu.service.core.MessageBean pushMsg) throws DaoException;
	
	public void saveMessage(dudu.service.dao.bean.MessageBean daoMsgBean) throws DaoException;
	
	public void updateMessage(String msgId, Boolean isRead, Date chgTime) throws DaoException;
	
	public List<dudu.service.dao.bean.MessageBean> getMessage(Long userId, int from, int to) throws DaoException;
	
	public List<dudu.service.dao.bean.MessageBean> getUserMessage(Long userId, int from, int to, List<String> types) throws DaoException;
	
	public List<dudu.service.dao.bean.MessageBean> getMessage(Long userId) throws DaoException;
	
	public long getEveryDayOnlineCount() throws BusinessException, DaoException, ParseException;
	
	public long getEveryDayActivateCount() throws BusinessException, DaoException, ParseException;
	
	public void updateMessageBat(List<String> ids, Boolean isRead, Date chgTime) throws DaoException;
}
