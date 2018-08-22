package com.ubo.common.terminal;

import dudu.service.core.terminal.BasicMessage;

public class UboCommandMessage extends BasicMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7400301600360037567L;
	
	private CommandType cmd;
	private String body;
	
	
	public UboCommandMessage(String eqId, CommandType cmd, String body) {
		this.eqId = eqId;
		this.cmd = cmd;
		this.body = body;
	}

	public CommandType getCmd() {
		return cmd;
	}

	public void setCmd(CommandType cmd) {
		this.cmd = cmd;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
