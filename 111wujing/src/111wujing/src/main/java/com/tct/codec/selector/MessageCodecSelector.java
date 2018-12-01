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
import com.tct.util.MessageTypeConstant;

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
		
		String msgType=json.getString("messageType");
		
		switch (msgType) {
		case MessageTypeConstant.MESSAGE01:
			msgCodec = new AuthorizationReqCodec();
			break;
		case MessageTypeConstant.MESSAGE03:
			msgCodec = new RegistReqCodec();
			break;
		case MessageTypeConstant.MESSAGE05:
			msgCodec = new BindingReqCodec();
			break;
		case MessageTypeConstant.MESSAGE06:
			msgCodec = new BindingResCodec();
			break;
		case MessageTypeConstant.MESSAGE07:
			msgCodec = new OutWarehouseReqCodec();
			break;
		case MessageTypeConstant.MESSAGE08:
			msgCodec = new OutWarehouseResCodec();
			break;
		case MessageTypeConstant.MESSAGE09:
			msgCodec = new CancelRecipientsGunReqCodec();
			break;
		case MessageTypeConstant.MESSAGE10:
			msgCodec = new CancelRecipientsGunResCodec();
			break;
		case MessageTypeConstant.MESSAGE11:
			msgCodec = new InWarehouseReqCodec();
			break;
		case MessageTypeConstant.MESSAGE12:
			msgCodec = new InWarehouseResCodec();
			break;
		case MessageTypeConstant.MESSAGE13:
			msgCodec = new CancelInWarehouseReqCodec();
			break;
		case MessageTypeConstant.MESSAGE14:
			msgCodec = new CancelInWarehouseResCodec();
			break;
		case MessageTypeConstant.MESSAGE15:
			msgCodec = new WatchHeartReqCodec();
			break;
		case MessageTypeConstant.MESSAGE17:
			msgCodec = new DeviceHeartReqCodec();
			break;
		case MessageTypeConstant.MESSAGE19:
			msgCodec = new StartStopSearchGunReqCodec();
			break;
		case MessageTypeConstant.MESSAGE21:
			msgCodec = new SearchGunReqCodec();
			break;
		case MessageTypeConstant.MESSAGE23:
			msgCodec = new ReportBulletNumberReqCodec();
			break;
		case MessageTypeConstant.MESSAGE25:
			msgCodec = new GetBulletNumberReqCodec();
			break;
		case MessageTypeConstant.MESSAGE26:
			msgCodec = new GetBulletNumberResCodec();
			break;
		case MessageTypeConstant.MESSAGE27:
			msgCodec = new ParamSettingReqCodec();
			break;
		default:
			msgCodec = null;
			break;
		}
		
		return msgCodec;
	}

/*	public MessageCodec encode(String outMsg) throws Exception {
		
		return null;
	}*/

}
