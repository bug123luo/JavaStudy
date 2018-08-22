package com.ubo.common.terminal;

import java.util.Date;

import dudu.service.core.terminal.BasicMessage;

public class UboLowbatMessage extends BasicMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 119536567939602947L;
	private Date t;

	public Date getT() {
		return t;
	}

	public void setT(Date t) {
		this.t = t;
	}

}
