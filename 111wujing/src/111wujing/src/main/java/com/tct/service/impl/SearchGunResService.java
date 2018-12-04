/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  SearchGunResService.java   
 * @Package com.tct.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年12月4日 下午2:59:57   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tct.codec.protocol.pojo.SearchGunResMessage;
import com.tct.db.dao.MessageRecordsDao;
import com.tct.db.dao.SosMsgDao;
import com.tct.db.po.MessageRecords;
import com.tct.util.StringConstant;

/**   
 * @ClassName:  SearchGunResService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年12月4日 下午2:59:57   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class SearchGunResService implements TemplateService {

	@Autowired
	SosMsgDao sosDao;
	
	@Autowired
	MessageRecordsDao mrDao;
	/**   
	 * <p>Title: handleCodeMsg</p>   
	 * <p>Description: </p>   
	 * @param msg
	 * @throws Exception   
	 * @see com.tct.service.impl.TemplateService#handleCodeMsg(java.lang.Object)   
	 */
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		
		SearchGunResMessage sgresMsg = (SearchGunResMessage)msg;
		if(sgresMsg.getMessageBody().getState().equals(StringConstant.SUCCESS_OLD_STATE)) {
			
			MessageRecords messageRecords=mrDao.selectBySerlNum(sgresMsg.getSerialNumber());
			
			JSONObject json=JSONObject.parseObject(messageRecords.getMessage());
			
			JSONArray jsonArray = json.getJSONArray("lostGunList");
			if(jsonArray.size()>0){
				  for(int i=0;i<jsonArray.size();i++){
				    JSONObject gun = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
				    String gunMac =gun.getString("gunMac");
				    sosDao.updateSelectiveByGunMac(gunMac);
				  }
			}
			
		}else {
			
		}
		
	}

}
