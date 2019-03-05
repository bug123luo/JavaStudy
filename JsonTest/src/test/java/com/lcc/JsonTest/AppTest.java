package com.lcc.JsonTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return (Test) new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    @Test
	public void strToJson() {
		JSONObject testjson = new JSONObject();
		JSONObject testjson2 = new JSONObject();
		JSONObject testjson3 = new JSONObject();
		JSONObject testjson4 = new JSONObject();
		JSONArray testjson5 = new JSONArray();
		JSONObject testjson6 = new JSONObject();
		JSONObject testjson7 = new JSONObject();

		testjson.put("version", "001");
		testjson.put("msgType", "warn");
		testjson.put("msgSerial", "46545654564654");
		testjson.put("msgSendTime", "20190102565689");
		testjson.put("msgBody", testjson2);
		testjson2.put("baseStation", testjson3);
		testjson3.put("serial", "909890");
		testjson3.put("repeater", testjson4);
		testjson4.put("devices", testjson5);
		testjson4.put("id","123213132");
		testjson5.add(testjson6);
		testjson5.add(testjson7);
		testjson6.put("number", "89798809809");
		testjson6.put("warn", "");
		System.out.println(testjson.toJSONString());
		String msg=testjson.toString();
		System.out.println(msg);
		//IotJsonMsg iotJsonMsg = IotStringToClass.changeToIotMsg(msg);
		
	}
}
