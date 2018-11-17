/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MessageRecordsDao.java   
 * @Package com.tct.db.dao   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月8日 上午11:38:59   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tct.codec.protocol.pojo.CancelInWarehouseReqMessage;
import com.tct.codec.protocol.pojo.CancelRecipientsGunReqMessage;
import com.tct.codec.protocol.pojo.CancelRecipientsGunResMessage;
import com.tct.codec.protocol.pojo.InWarehouseReqMessage;
import com.tct.db.mapper.MessageRecordsCustomMapper;
import com.tct.db.po.MessageRecordsCustom;
import com.tct.db.po.MessageRecordsQueryVo;

import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName:  MessageRecordsDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月8日 上午11:38:59   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Slf4j
@Component
public class MessageRecordsDaoImpl implements MessageRecordsDao{

	@Autowired
	MessageRecordsCustomMapper msgDao;
	
	public int insertSelective(CancelRecipientsGunReqMessage cRecGunReqMsg) {
		
		MessageRecordsCustom mRecCustom = new MessageRecordsCustom();
		mRecCustom.setGunId(cRecGunReqMsg.getMessageBody().getGunId());
		mRecCustom.setSerlNum(cRecGunReqMsg.getSerialNumber());
		mRecCustom.setMessage(JSONObject.toJSONString(cRecGunReqMsg));
		
		int i =0;
		i= msgDao.insertSelective(mRecCustom);
		return i;
	}
	
	public MessageRecordsCustom selectBySerlNum(CancelRecipientsGunResMessage cRecGunResMsg) {
		MessageRecordsQueryVo messageRecordsQueryVo = new MessageRecordsQueryVo();
		MessageRecordsCustom mRecCustomTemp = new MessageRecordsCustom();
		mRecCustomTemp.setSerlNum(cRecGunResMsg.getSerialNumber());
		MessageRecordsCustom mRecCustom=msgDao.selectBySerlNum(messageRecordsQueryVo);
		return mRecCustom;
	}
	
	public MessageRecordsCustom selectBySerlNum(String serialNumber) {
		MessageRecordsQueryVo messageRecordsQueryVo = new MessageRecordsQueryVo();
		MessageRecordsCustom mRecCustomTemp = new MessageRecordsCustom();
		mRecCustomTemp.setSerlNum(serialNumber);
		MessageRecordsCustom mRecCustom=msgDao.selectBySerlNum(messageRecordsQueryVo);
		return mRecCustom;
	}
	
	public int insertSelective(InWarehouseReqMessage inWhReqMsg) {
		MessageRecordsCustom mRecCustom = new MessageRecordsCustom();
		
		mRecCustom.setGunId(inWhReqMsg.getMessageBody().getGunId());
		mRecCustom.setSerlNum(inWhReqMsg.getSerialNumber());
		mRecCustom.setMessage(JSONObject.toJSONString(inWhReqMsg));
		
		int i =0;
		i= msgDao.insertSelective(mRecCustom);
		return i;
	}
	
	public int insertSelective(CancelInWarehouseReqMessage msg) {
		MessageRecordsCustom mRecCustom = new MessageRecordsCustom();
		
		mRecCustom.setGunId(msg.getMessageBody().getGunId());
		mRecCustom.setSerlNum(msg.getSerialNumber());
		mRecCustom.setMessage(JSONObject.toJSONString(msg));
		
		int i =0;
		i= msgDao.insertSelective(mRecCustom);
		return i;
	}
}
