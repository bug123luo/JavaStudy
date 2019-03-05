package com.lcc.JsonTest;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        strToJson();
        
        System.out.println("-------------------------------------------------");
    }

	public static void strToJson() {
		JSONObject testjson = new JSONObject();
		JSONObject testjson2 = new JSONObject();
		JSONObject testjson3 = new JSONObject();
		JSONObject testjson4 = new JSONObject();
		JSONArray testjson5 = new JSONArray();
		JSONObject testjson6 = new JSONObject();
		JSONObject testjson7 = new JSONObject();

		testjson6.put("number", "98809809");
		testjson6.put("warn", "");
		testjson5.add(testjson6);
		//testjson5.add(testjson7);
		testjson4.put("devices", testjson5);
		testjson4.put("id","123213132");
		testjson3.put("serial", "909890");
		testjson3.put("repeater", testjson4);
		testjson2.put("baseStation", testjson3);
		testjson.put("version", "001");
		testjson.put("msgType", "warn");
		testjson.put("msgSerial", "46545654564654");
		testjson.put("msgSendTime", "20190102565689");
		testjson.put("msgBody", testjson2);
		

		System.out.println(testjson.toJSONString());
		String msg=testjson.toString();
		
		System.out.println(msg);
		IotJsonMsg iotJsonMsg = IotStringToClass.changeToIotMsg(msg);
		System.out.println(iotJsonMsg.toString());
	}
}
