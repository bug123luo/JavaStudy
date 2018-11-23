package com.tct.wujing;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tct.codec.protocol.pojo.BindingReqMessage;
import com.tct.codec.protocol.pojo.BindingReqMessageBody;
import com.tct.codec.protocol.pojo.BindingReqMessageBodyGunInfo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {

        String jsonstr = "{\"deviceType\":\"2\",\"formatVersion\":\"0001\",\"messageBody\":{\"gunList\":[{\"gunId\":\"8651234215\",\"gunMac\":\"25:23:3t:6g:26:8e\",\"gunModel\":\"QBZ95B-1式5.8mm短自动步枪\",\"gunType\":\"2\"},{\"gunId\":\"8651234215\",\"gunMac\":\"25:23:3t:6g:26:8e\",\"gunModel\":\"QBZ95B-1式5.8mm短自动步枪\",\"gunType\":\"2\"},{\"gunId\":\"8651234215\",\"gunMac\":\"25:23:3t:6g:26:8e\",\"gunModel\":\"QBZ95B-1式5.8mm短自动步枪\",\"gunType\":\"2\"},{\"gunId\":\"8651234215\",\"gunMac\":\"25:23:3t:6g:26:8e\",\"gunModel\":\"QBZ95B-1式5.8mm短自动步枪\",\"gunType\":\"2\"},{\"gunId\":\"8651234215\",\"gunMac\":\"25:23:3t:6g:26:8e\",\"gunModel\":\"QBZ95B-1式5.8mm短自动步枪\",\"gunType\":\"2\"}],\"userId\":\"\"},\"messageType\":\"05\",\"sendTime\":\"20181123034208\",\"serialNumber\":\"201811230342080002\",\"sessionToken\":\"000001673f84d26a\",\"uniqueIdentification\":\"357000066159864\"}";
        
        JSONObject jsonObject = JSONObject.parseObject(jsonstr);
        
		BindingReqMessage msg = new BindingReqMessage();
		msg.setUniqueIdentification(jsonObject.getString("uniqueIdentification"));
		msg.setDeviceType(jsonObject.getInteger("deviceType"));
		msg.setFormatVersion(jsonObject.getString("formatVersion"));
		msg.setMessageType(jsonObject.getString("messageType"));
		msg.setSendTime(jsonObject.getString("sendTime"));
		msg.setSerialNumber(jsonObject.getString("serialNumber"));
		msg.setSessionToken(jsonObject.getString("sessionToken"));
		msg.setMessageBody(jsonObject.getObject("messageBody", BindingReqMessageBody.class));
/*		JSONObject jsonObject2 = jsonObject.getJSONObject("messageBody");
		JSONArray jsonArray = jsonObject2.getJSONArray("gunList");
		BindingReqMessageBody bindingReqMessageBody = new BindingReqMessageBody();
		List<BindingReqMessageBodyGunInfo> list =  JSONObject.parseArray(jsonArray.toJSONString(), BindingReqMessageBodyGunInfo.class);
		bindingReqMessageBody.setUserId(jsonObject2.getString("userId"));
		bindingReqMessageBody.setGunList((ArrayList<BindingReqMessageBodyGunInfo>) list);*/
		//System.out.println(jsonObject2.toJSONString());
		
/*		System.out.print(msg.getMessageBody().toString());
*/        System.out.println( "Hello World!" );
    }
}
