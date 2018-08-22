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

import java.util.List;

import dudu.service.dao.bean.AccountBeanEx;

public interface AccountService {
	
	public Boolean isPhoneNumberPresent(String phoneNumber) throws BusinessException;
	public Long register(String phoneNumber, String password) throws BusinessException;
	public Long authenticate(String phoneNumber, String password) throws BusinessException;
	public void resetPassword(String phoneNumber, String password) throws BusinessException;
	public Boolean checkUserById(long userId) throws BusinessException;
	public Boolean updateAvators(String icon, long userId) throws BusinessException;
	public String getAvators(long userId) throws BusinessException;
	public void updateIosToken(String phoneNumber, String iosToken) throws BusinessException;
	public AccountBeanEx getAccountById(Long userId) throws BusinessException;
	public void updateIosTokById(Long userId, String iosToken) throws BusinessException;
	public List<AccountBeanEx> getAccountByUnionId(String unionid) throws BusinessException;
	public void saveWeixId( String unionid,String phone) throws BusinessException;
	public AccountBeanEx isUnionId(String unionId) throws BusinessException;
	public void updatePasswordById(long id, String password,String phone) throws BusinessException;
}
