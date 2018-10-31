/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  RegistReqCodec.java   
 * @Package com.tct.codec.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年10月31日 下午3:56:17   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.codec.impl;

import org.apache.ibatis.reflection.wrapper.BaseWrapper;

import com.alibaba.fastjson.JSONObject;
import com.tct.codec.protocol.pojo.RegistReqMessage;
import com.tct.codec.protocol.pojo.RegistReqMessageBody;

/**   
 * @ClassName:  RegistReqCodec   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年10月31日 下午3:56:17   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class RegistReqCodec implements MessageBodyCodec {

	/**   
	 * <p>Title: decode</p>   
	 * <p>Description: </p>   
	 * @param json
	 * @return
	 * @throws Exception   
	 * @see com.tct.codec.impl.MessageBodyCodec#decode(com.alibaba.fastjson.JSONObject)   
	 */
	@Override
	public Object decode(JSONObject json) throws Exception {
		RegistReqMessage msg = new RegistReqMessage();
		msg.setUniqueIdentification(json.getString("uniqueIdentification"));
		msg.setDeviceType(json.getString("deviceType"));
		msg.setFormatVersion(json.getString("formatVersion"));
		msg.setMessageType(json.getString("messageType"));
		msg.setSendTime(json.getString("sendTime"));
		msg.setSerialNumber(json.getString("serialNumber"));
		msg.setSessionToken(json.getString("sessionToken"));
		msg.setMessageBody(json.getObject("messageBody", RegistReqMessageBody.class));
		return msg;
	}

	/**   
	 * <p>Title: encode</p>   
	 * <p>Description: </p>   
	 * @param outMsg
	 * @return
	 * @throws Exception   
	 * @see com.tct.codec.impl.MessageBodyCodec#encode(java.lang.Object)   
	 */
	@Override
	public String encode(Object outMsg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
