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

import dudu.service.dao.bean.AccountBeanEx;
import dudu.service.dao.bean.SystemNoticeBean;

import java.util.List;

public class SystemNoticeDao extends DuduDaoSupport {


    public SystemNoticeDao() {

    }

    public void save(SystemNoticeBean systemNoticeBean) throws DaoException {

        super.save(systemNoticeBean);
    }

    public SystemNoticeBean getNew()
            throws DaoException {

        String hql = String.format(
                "FROM SystemNoticeBean s order by s.createDate desc");

        return (SystemNoticeBean) super.query(hql).get(0);
    }

    public SystemNoticeBean get(Long id)
            throws DaoException {

        String hql = String.format(
                "FROM SystemNoticeBean WHERE id='%s'", id);

        return (SystemNoticeBean) super.uniqueResult(hql);
    }

    public List<SystemNoticeBean> getAll()
            throws DaoException {

        String hql = String.format(
                "From SystemNoticeBean"
        );

        List<SystemNoticeBean> systemNoticeBeanList = super.query(hql);
        return systemNoticeBeanList;
    }

}
