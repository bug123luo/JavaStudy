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

import dudu.service.common.BusinessErrorConst;
import dudu.service.common.BusinessException;
import dudu.service.common.SystemNoticeService;
import dudu.service.dao.DaoException;
import dudu.service.dao.SystemNoticeDao;
import dudu.service.dao.bean.AccountBeanEx;
import dudu.service.dao.bean.SystemNoticeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SystemNoticeImpl implements SystemNoticeService {

    private static Logger logger = LoggerFactory.getLogger(SystemNoticeImpl.class);

    private SystemNoticeDao systemNoticeDao;

    public SystemNoticeImpl() {

    }

    public SystemNoticeDao getSystemNoticeDao() {
        return systemNoticeDao;
    }

    public void setSystemNoticeDao(SystemNoticeDao systemNoticeDao) {
        this.systemNoticeDao = systemNoticeDao;
    }


    @Override
    public SystemNoticeBean getNew() throws BusinessException {
        logger.debug(".getNew()");
        try {

            return systemNoticeDao.getNew();
        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }
    }


    @Override
    public List<SystemNoticeBean> getAll() throws BusinessException {
        // TODO Auto-generated method stub
        logger.debug(".getAll(userId={})");
        try {
            return systemNoticeDao.getAll();
        } catch (DaoException daoException) {

            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }
    }
}
