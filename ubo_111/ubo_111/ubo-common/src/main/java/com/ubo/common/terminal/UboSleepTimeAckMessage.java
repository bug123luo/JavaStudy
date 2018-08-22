package com.ubo.common.terminal;

import dudu.service.core.terminal.BasicMessage;

public class UboSleepTimeAckMessage extends BasicMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8188077907906985231L;
	private boolean isSuccess;
	
	public UboSleepTimeAckMessage() {
		
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
}
