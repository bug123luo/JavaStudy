package com.ubo.common.terminal;

import dudu.service.core.terminal.BasicMessage;

public class UboLoginRespAck extends BasicMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1162077770837074000L;
	private boolean isSuccess;
	
	public UboLoginRespAck() {
		
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
}
