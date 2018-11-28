/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  MessageCodecSelector.java   
 * @Package com.tct.codec.selector   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年10月30日 下午3:34:17   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.codec.selector;

import com.alibaba.fastjson.JSONObject;
import com.tct.codec.impl.AuthorizationReqCodec;
import com.tct.codec.impl.BindingReqCodec;
import com.tct.codec.impl.BindingResCodec;
import com.tct.codec.impl.CancelInWarehouseReqCodec;
import com.tct.codec.impl.CancelInWarehouseResCodec;
import com.tct.codec.impl.CancelRecipientsGunReqCodec;
import com.tct.codec.impl.CancelRecipientsGunResCodec;
import com.tct.codec.impl.DeviceHeartReqCodec;
import com.tct.codec.impl.GetBulletNumberReqCodec;
import com.tct.codec.impl.GetBulletNumberResCodec;
import com.tct.codec.impl.InWarehouseReqCodec;
import com.tct.codec.impl.InWarehouseResCodec;
import com.tct.codec.impl.MessageBodyCodec;
import com.tct.codec.impl.OutWarehouseReqCodec;
import com.tct.codec.impl.OutWarehouseResCodec;
import com.tct.codec.impl.ParamSettingReqCodec;
import com.tct.codec.impl.RegistReqCodec;
import com.tct.codec.impl.ReportBulletNumberReqCodec;
import com.tct.codec.impl.SearchGunReqCodec;
import com.tct.codec.impl.StartStopSearchGunReqCodec;
import com.tct.codec.impl.WatchHeartReqCodec;

/**   
 * @ClassName:  MessageCodecSelector   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年10月30日 下午3:34:17   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class MessageCodecSelector {
	public MessageBodyCodec getMessageDecode(JSONObject tempjson) throws Exception {
		JSONObject json= tempjson;
		
		MessageBodyCodec msgCodec = null;
		
		if (json.getString("messageType").equals("01")) {
			msgCodec = new AuthorizationReqCodec();
		}else if(json.getString("messageType").equals("03")) {
			msgCodec = new RegistReqCodec();
		}else if(json.getString("messageType").equals("05")) {
			msgCodec = new BindingReqCodec();
		}else if(json.getString("messageType").equals("07")) {
			msgCodec = new OutWarehouseReqCodec();
		}else if(json.getString("messageType").equals("09")) {
			msgCodec = new CancelRecipientsGunReqCodec();
		}else if(json.getString("messageType").equals("11")){
		    msgCodec = new InWarehouseReqCodec();	
		}else if(json.getString("messageType").equals("13")) {
			msgCodec = new CancelInWarehouseReqCodec();
		}else if(json.getString("messageType").equals("15")) {
			msgCodec = new WatchHeartReqCodec();
		}else if(json.getString("messageType").equals("17")) {
			msgCodec = new DeviceHeartReqCodec();
		}else if(json.getString("messageType").equals("19")){
			msgCodec = new StartStopSearchGunReqCodec();
		}else if(json.getString("messageType").equals("21")) {
			msgCodec = new SearchGunReqCodec();
		}else if(json.getString("messageType").equals("23")) {
			msgCodec = new ReportBulletNumberReqCodec();
		}else if(json.getString("messageType").equals("25")) {
			msgCodec = new GetBulletNumberReqCodec();
		}else if(json.getString("messageType").equals("27")) {
			msgCodec = new ParamSettingReqCodec();
		}else if (json.getString("messageType").equals("06")) {
			msgCodec = new BindingResCodec();
		}else if(json.getString("messageType").equals("08")) {
			msgCodec = new OutWarehouseResCodec();
		}else if(json.getString("messageType").equals("10")){
			msgCodec = new CancelRecipientsGunResCodec();
		}else if(json.getString("messageType").equals("12")){
			msgCodec = new InWarehouseResCodec();
		}else if (json.getString("messageType").equals("14")) {
			msgCodec = new CancelInWarehouseResCodec();
		}else if(json.getString("messageType").equals("26")){
			msgCodec = new GetBulletNumberResCodec();
		}else {
			msgCodec = null;
		}
				
		return msgCodec;
	}

/*	public MessageCodec encode(String outMsg) throws Exception {
		
		return null;
	}*/

}
