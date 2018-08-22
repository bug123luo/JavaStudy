/**
 * 
 */
package com.ubo.common.terminal;

import dudu.service.core.terminal.BasicMessage;

/**
 * @author mrgdren
 *
 */
public class BluetoothFollowMessage extends BasicMessage {

	private static final long serialVersionUID = 5481832928800965886L;
	public static final String MODE_DEFUALT   = "0";
	public static final String MODE_FOLLOW = "1";
	public static final String MODE_FAR_AWAY = "2";
	
	private String flag;
	
	public BluetoothFollowMessage() {
		
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	

}
