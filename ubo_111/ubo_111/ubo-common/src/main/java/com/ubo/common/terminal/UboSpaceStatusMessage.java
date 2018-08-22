/**
 * 
 */
package com.ubo.common.terminal;

import dudu.service.core.terminal.BasicMessage;

/**
 * @author mrgdren
 *
 */
public class UboSpaceStatusMessage extends BasicMessage {

	private static final long serialVersionUID = 5481833927007965886L;

	private String status;
	
	public static final String EMPTY = "1";
	public static final String FULL  = "0";
	
	public UboSpaceStatusMessage() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
