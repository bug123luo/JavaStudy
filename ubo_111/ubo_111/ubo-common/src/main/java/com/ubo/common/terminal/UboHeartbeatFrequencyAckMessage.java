package com.ubo.common.terminal;

import dudu.service.core.terminal.BasicMessage;

public class UboHeartbeatFrequencyAckMessage extends BasicMessage {
	
	private static final long serialVersionUID = -3127729982067365422L;
	private boolean isSuccess;
	
	public UboHeartbeatFrequencyAckMessage() {
		
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
}
