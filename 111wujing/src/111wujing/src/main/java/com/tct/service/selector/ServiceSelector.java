/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  ServiceSelector.java   
 * @Package com.tct.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年10月31日 上午11:04:41   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.service.selector;

import javax.annotation.Resource;
import com.alibaba.fastjson.JSONObject;
import com.tct.codec.impl.MessageBodyCodec;
import com.tct.codec.protocol.pojo.AuthorizationReqMessage;
import com.tct.codec.protocol.pojo.BindingReqMessage;
import com.tct.codec.protocol.pojo.BindingResMessage;
import com.tct.codec.protocol.pojo.CancelInWarehouseReqMessage;
import com.tct.codec.protocol.pojo.CancelInWarehouseResMessage;
import com.tct.codec.protocol.pojo.CancelRecipientsGunReqMessage;
import com.tct.codec.protocol.pojo.CancelRecipientsGunResMessage;
import com.tct.codec.protocol.pojo.DeviceHeartReqMessage;
import com.tct.codec.protocol.pojo.GetBulletNumberReqMessage;
import com.tct.codec.protocol.pojo.GetBulletNumberResMessage;
import com.tct.codec.protocol.pojo.InWarehouseReqMessage;
import com.tct.codec.protocol.pojo.InWarehouseResMessage;
import com.tct.codec.protocol.pojo.OutWarehouseReqMessage;
import com.tct.codec.protocol.pojo.OutWarehouseResMessage;
import com.tct.codec.protocol.pojo.ParamSettingReqMessage;
import com.tct.codec.protocol.pojo.RegistReqMessage;
import com.tct.codec.protocol.pojo.ReportBulletNumberReqMessage;
import com.tct.codec.protocol.pojo.SearchGunReqMessage;
import com.tct.codec.protocol.pojo.StartStopSearchGunReqMessage;
import com.tct.codec.protocol.pojo.WatchHeartReqMessage;
import com.tct.util.MessageTypeConstant;
import com.tct.util.SpringContextUtil;
import com.tct.service.impl.AuthorizationReqService;
import com.tct.service.impl.BindingReqService;
import com.tct.service.impl.BindingResService;
import com.tct.service.impl.CancelInWarehouseReqService;
import com.tct.service.impl.CancelInWarehouseResService;
import com.tct.service.impl.CancelRecipientsGunReqService;
import com.tct.service.impl.CancelRecipientsGunResService;
import com.tct.service.impl.DeviceHeartReqService;
import com.tct.service.impl.GetBulletNumberReqService;
import com.tct.service.impl.GetBulletNumberResService;
import com.tct.service.impl.InWarehouseReqService;
import com.tct.service.impl.InWarehouseResService;
import com.tct.service.impl.OutWarehouseReqService;
import com.tct.service.impl.OutWarehouseResService;
import com.tct.service.impl.ParamSettingReqService;
import com.tct.service.impl.RegistReqService;
import com.tct.service.impl.ReportBulletNumberReqService;
import com.tct.service.impl.SearchGunReqService;
import com.tct.service.impl.StartStopSearchGunReqService;
import com.tct.service.impl.TemplateService;
import com.tct.service.impl.WatchHeartReqService;


/**   
 * @ClassName:  ServiceSelector   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年10月31日 上午11:04:41   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

public class ServiceSelector {
	
	@Resource(name = "authorizationReqService")
	private AuthorizationReqService authorizationReqService;
	
	@Resource(name = "bindingReqService")
	private BindingReqService bindingReqService;
	
	@Resource(name = "cancelInWarehouseReqService")
	private CancelInWarehouseReqService cancelInWarehouseReqService;
	
	@Resource(name = "cancelRecipientsGunReqService")
	private CancelRecipientsGunReqService cancelRecipientsGunReqService;
	
	@Resource(name = "deviceHeartReqService")
	private DeviceHeartReqService deviceHeartReqService;
	
	@Resource(name = "getBulletNumberReqService")
	private GetBulletNumberReqService getBulletNumberReqService;
	
	@Resource(name = "inWarehouseReqService")
	private InWarehouseReqService inWarehouseReqService;
	
	@Resource(name = "outWarehouseReqService")
	private OutWarehouseReqService outWarehouseReqService;
	
	@Resource(name = "paramSettingReqService")
	private ParamSettingReqService paramSettingReqService;
	
	@Resource(name = "registerableService")
	private RegistReqService registReqService;
	
	@Resource(name = "reportBulletNumberReqService")
	private ReportBulletNumberReqService reportBulletNumberReqService;
	
	@Resource(name = "searchGunReqService")
	private SearchGunReqService searchGunReqService;
	
	@Resource(name = "startStopSearchGunReqService")
	private StartStopSearchGunReqService startStopSearchGunReqService;
	
	@Resource(name = "watchHeartReqService")
	private WatchHeartReqService watchHeartReqService;
	
	@Resource(name = "bindingResService")
	private BindingResService bindingResService;
	
	@Resource(name = "cancelRecipientsGunResService")
	private CancelRecipientsGunResService cancelRecipientsGunResService;
	
	@Resource(name="inWarehouseResService")
	private InWarehouseResService inWarehouseResService;
	
	@Resource(name="getBulletNumberResService")
	private GetBulletNumberResService getBulletNumberResService;
	
	@Resource(name="outWarehouseResService")
	private OutWarehouseResService outWarehouseResService;
	
	@Resource(name="cancelInWarehouseResService")
	private CancelInWarehouseResService cancelInWarehouseResService;
	
	public void handlerService(MessageBodyCodec messageCodec,JSONObject msg){
		
		TemplateService service =null;
		Object objmsg = null;
		JSONObject json= msg;
		
		String msgType=json.getString("messageType");
		
		switch (msgType) {
			case MessageTypeConstant.MESSAGE01:
				service = SpringContextUtil.getBean("authorizationReqService");	
				try {
					objmsg = (AuthorizationReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE03:
				service = SpringContextUtil.getBean("registReqService");
				try {
					objmsg = (RegistReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE05:
				service = SpringContextUtil.getBean("bindingReqService");
				try {
					objmsg = (BindingReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE06:
				service = SpringContextUtil.getBean("bindingResService");
				try {
					objmsg = (BindingResMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE07:
				service = SpringContextUtil.getBean("outWarehouseReqService");
				try {
					objmsg = (OutWarehouseReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE08:
				service = SpringContextUtil.getBean("outWarehouseResService");
				try {
					objmsg = (OutWarehouseResMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE09:
				service = SpringContextUtil.getBean("cancelRecipientsGunReqService");
				try {
					objmsg = (CancelRecipientsGunReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE10:
				service = SpringContextUtil.getBean("cancelRecipientsGunResService");
				try {
					objmsg = (CancelRecipientsGunResMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE11:
				service = SpringContextUtil.getBean("inWarehouseReqService");
				try {
					objmsg = (InWarehouseReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE12:
				service = SpringContextUtil.getBean("inWarehouseResService");
				try {
					objmsg = (InWarehouseResMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE13:
				service = SpringContextUtil.getBean("cancelInWarehouseReqService");
				try {
					objmsg = (CancelInWarehouseReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE14:
				service = SpringContextUtil.getBean("cancelInWarehouseResService");
				try {
					objmsg = (CancelInWarehouseResMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE15:
				service = SpringContextUtil.getBean("watchHeartReqService");
				try {
					objmsg = (WatchHeartReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE17:
				service = SpringContextUtil.getBean("deviceHeartReqService");
				try {
					objmsg = (DeviceHeartReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE19:
				service = SpringContextUtil.getBean("startStopSearchGunReqService");
				try {
					objmsg = (StartStopSearchGunReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE21:
				service = SpringContextUtil.getBean("searchGunReqService");
				try {
					objmsg = (SearchGunReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE23:
				service = SpringContextUtil.getBean("reportBulletNumberReqService");
				try {
					objmsg = (ReportBulletNumberReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE25:
				service = SpringContextUtil.getBean("getBulletNumberReqService");
				try {
					objmsg = (GetBulletNumberReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE26:
				service = SpringContextUtil.getBean("getBulletNumberResService");
				try {
					objmsg = (GetBulletNumberResMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MessageTypeConstant.MESSAGE27:
				service = SpringContextUtil.getBean("paramSettingReqService");
				try {
					objmsg = (ParamSettingReqMessage)messageCodec.decode(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}		
		
		try {
			service.handleCodeMsg(objmsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
