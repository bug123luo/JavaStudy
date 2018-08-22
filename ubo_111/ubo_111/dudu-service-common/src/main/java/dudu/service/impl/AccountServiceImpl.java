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
package dudu.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.common.AccountService;
import dudu.service.common.BusinessErrorConst;
import dudu.service.common.BusinessException;
import dudu.service.core.utils.MD5;
import dudu.service.dao.AccountDao;
import dudu.service.dao.DaoException;
import dudu.service.dao.bean.AccountBean;
import dudu.service.dao.bean.AccountBeanEx;

public class AccountServiceImpl implements AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private AccountDao accountDao;

    public AccountServiceImpl() {

    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Long register(String phoneNumber, String password)
            throws BusinessException {

        logger.debug(".register(pn={}, p={})", phoneNumber, password);

        try {

            if (accountDao.isPhoneNumberPresent(phoneNumber)) {
                throw new BusinessException(
                        BusinessErrorConst.PhoneNumberIsPresent,
                        "Phone number is present.");
            }

            AccountBean accountBean = new AccountBean();
            long timestamp = System.currentTimeMillis() / 1000;

            accountBean.setPhone(phoneNumber);
            accountBean.setPassword(password);
            accountBean.setSalt("");
            accountBean.setCrtTime(timestamp);
            accountBean.setChgTime(timestamp);
            accountDao.save(accountBean);

            return accountBean.getUserId();
        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());

        }
    }

    @Override
    public Long authenticate(String phoneNumber, String password)
            throws BusinessException {

        logger.debug(".authenticate(uid={}, p={})", phoneNumber, password);
        System.out.println(String.format(".authenticate(uid=%s, p=%s)", phoneNumber, password));

        try {
            AccountBeanEx accountBeanEx = accountDao.get(phoneNumber);

            //AccountBean accountBean = accountDao.get(phoneNumber, password);
            //AccountBean accountBean = accountDao.get(phoneNumber);

            if (accountBeanEx == null) {
                throw new BusinessException(
                        BusinessErrorConst.IncorrectAccountOrPassword,
                        "Incorrect account or password");
            }

            String pwd = password.toUpperCase();
            String salt = accountBeanEx.getSalt();

            logger.debug(".authenticate(pwd={}, salt={})", pwd, salt);

            System.out.println(String.format(".authenticate(pwd=%s, salt=%s)", pwd, salt));


            if (salt != null && !"".equals(salt) && !"NULL".equals(salt) && !"null".equals(salt)) {

                String newmd5 = MD5.getMessageDigest((pwd + salt).getBytes());
                String dbPwd = accountBeanEx.getPasswd();

                logger.debug(".authenticate(newmd5={}, dbPwd={})", newmd5, dbPwd);

                System.out.println(String.format(".authenticate(newmd5=%s, dbPwd=%s)", newmd5, dbPwd));


                if (!newmd5.equalsIgnoreCase(dbPwd)) {
                    throw new BusinessException(
                            BusinessErrorConst.IncorrectAccountOrPassword,
                            "Incorrect account or password");
                }

            } else {
                if (!password.equalsIgnoreCase(accountBeanEx.getPasswd())) {
                    throw new BusinessException(
                            BusinessErrorConst.IncorrectAccountOrPassword,
                            "Incorrect account or password");
                }
            }

            return accountBeanEx.getId();

        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }

    }

    @Override
    public void resetPassword(String phoneNumber, String password)
            throws BusinessException {

        logger.debug(".resetPassword(pn={}, p={})", phoneNumber, password);

        try {
            if (!accountDao.isPhoneNumberPresent(phoneNumber)) {
                throw new BusinessException(
                        BusinessErrorConst.PhoneNumberIsNotPresent,
                        "Phone number is not present.");
            }

            accountDao.updatePassword(phoneNumber, password);
        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage());
        }

    }

    @Override
    public Boolean isPhoneNumberPresent(String phoneNumber)
            throws BusinessException {

        logger.debug(".isPhoneNumberPresent(pn={})", phoneNumber);

        try {
            return accountDao.isPhoneNumberPresent(phoneNumber);
        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }
    }

    @Override
    public Boolean checkUserById(long userId) throws BusinessException {
        logger.debug(".checkUserById(userId={})", userId);
        try {
            String phone = accountDao.getUserPhone(userId);
            if (phone != null)
                return true;
        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }
        return false;
    }

    @Override
    public Boolean updateAvators(String icon, long userId) throws BusinessException {

        logger.debug(".updateAvators(logo={})", icon);
        try {
            accountDao.updateAvator(icon, userId);
            return true;
        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }
    }

    @Override
    public String getAvators(long userId) throws BusinessException {
        // TODO Auto-generated method stub
        logger.debug(".getAvators(userId={})", userId);
        try {
            return accountDao.getAvator(userId);
        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }
    }

    @Override
    public void updateIosToken(String phoneNumber, String iosToken)
            throws BusinessException {

        logger.debug(".updateIosToken(phone={}, tok={})", phoneNumber, iosToken);
        System.out.println(String.format(".authenticate(phone=%s, tok=%s)", phoneNumber, iosToken));

        try {
            accountDao.updateIosToken(phoneNumber, iosToken);

        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }

    }

    @Override
    public AccountBeanEx getAccountById(Long userId)
            throws BusinessException {

        logger.debug(".getAccountById(userId={}", userId);
        System.out.println(String.format(".getAccountById(userId=%s)", userId));

        try {
            return accountDao.getById(userId);

        } catch (DaoException daoException) {
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }

    }

    @Override
    public void updateIosTokById(Long userId, String iosToken)
            throws BusinessException {

        logger.debug(".updateIosToken(userId={}, tok={})", userId, iosToken);
        System.out.println(String.format(".authenticate(phone=%s, tok=%s)", userId, iosToken));

        try {
            AccountBeanEx accountBeanEx = accountDao.getById(userId);
            String newToken="";
            if (accountBeanEx != null) {
                if (StringUtils.isNotEmpty(accountBeanEx.getIosToken())) {
                    String userToken="";
                    String teaToken = "";
                    if (accountBeanEx.getIosToken().contains("@")) {
                        String[] tokens = accountBeanEx.getIosToken().split("@");
                        if(tokens.length>0) {
                            userToken = tokens[0];
                            if (tokens.length > 1) {
                                teaToken = tokens[1];
                            }
                        }
                    } else{
                        userToken= accountBeanEx.getIosToken();
                    }
                    if(iosToken!=null&&iosToken!="") {
                        if (iosToken.contains("@")) {
                            newToken = userToken + iosToken;
                        } else {
                            newToken = iosToken + "@" + teaToken;
                        }
                    }
                } else {
                    newToken = iosToken;
                }
                accountDao.updateIosToken(accountBeanEx.getPhone(), newToken);
            }
        } catch (DaoException daoException) {
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }

    }

    /**
     * ͨ��΢�ŵ�¼unionid��ȡ�û���Ϣ
     *
     * @param unionId
     * @return
     * @throws BusinessException
     */
    @Override
    public List<AccountBeanEx> getAccountByUnionId(String unionId) throws BusinessException {

        logger.debug(".getAccountByUnionId(unionId={})", unionId);
        System.out.println(String.format(".getAccountByUnionId(unionId=%s)", unionId));

        try {
            return accountDao.getByUnionId(unionId);
        } catch (DaoException daoException) {
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }
    }

    /**
     * ����΢�ŵ�¼��openid,unionid
     * @param unionid
     * @throws BusinessException
     */

    @Override
    public void saveWeixId(String unionid,String phone)
            throws BusinessException {

        logger.debug(".getAccountByUnionId(unionId={},phone={})", unionid,phone);
        System.out.println(String.format(".saveWeixId(unionid=%s,phone=%s)", unionid,phone));

        try {
             accountDao.saveWeixId(unionid,phone);
        } catch (DaoException daoException) {
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }

    }
    
    @Override
    public AccountBeanEx isUnionId(String unionId) throws BusinessException {

        logger.debug(".getAccountByUnionId(unionId={})", unionId);
        System.out.println(String.format(".getAccountByUnionId(unionId=%s)", unionId));

        try {
        	List<AccountBeanEx> accounts=accountDao.getByUnionId(unionId);
            return accounts.isEmpty()?null:accounts.get(0);
        } catch (DaoException daoException) {
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }
    }

    @Override
    public void updatePasswordById(long id, String password,String phone)
            throws BusinessException {

        logger.debug(".resetPassword(pn={}, p={})", id, password);

        try {

            accountDao.updatePasswordById(id, password,phone);
        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage());
        }

    }
}
