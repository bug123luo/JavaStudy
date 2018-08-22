package com.ubo.common.terminal;

import dudu.service.core.terminal.CloseMessage;

public class UboCloseMessage extends CloseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2855282432973303759L;

	private byte closeType;
	private float voltage;
	private int eQuantity;
	
	public UboCloseMessage() {
		
	}

	public byte getCloseType() {
		return closeType;
	}

	public void setCloseType(byte closeType) {
		this.closeType = closeType;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

	public int getEQuantity() {
		return eQuantity;
	}

	public void setEQuantity(int eQuantity) {
		this.eQuantity = eQuantity;
	}
	
}
