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
package dudu.service.dao.bean.common;

import java.io.Serializable;
import java.sql.Timestamp;

public class ComBean implements Serializable {


    private static final long serialVersionUID = 5456096809813268329L;

    /**
     * 状态
     */
    private String status;
    /**
     * 创建时间
     */
    private Timestamp crtTime;
    /**
     * 修改时间
     */
    private Timestamp chgTime;
    /**
     * delete
     */
    private String delete;


    public ComBean() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Timestamp crtTime) {
        this.crtTime = crtTime;
    }

    public Timestamp getChgTime() {
        return chgTime;
    }

    public void setChgTime(Timestamp chgTime) {
        this.chgTime = chgTime;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }
}
