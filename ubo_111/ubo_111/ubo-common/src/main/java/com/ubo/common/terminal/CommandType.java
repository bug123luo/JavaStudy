package com.ubo.common.terminal;

import io.netty.buffer.ByteBuf;

public enum CommandType {
	LOGIN_COMMAND("LOGIN", "01"),
	SYNC_COMMAND("SYNC", "02"),
	LOGOUT_COMMAND("LOGOUT", "04"),
	LOCATION_COMMAND("LOCATION", "07"),
	GUARDAREA_COMMAND("GUARDAREA", "09"),
	LOCATIONPAT_COMMAND("LOCATIONPAT", "34"),
	SLEEPTIME_COMMAND("SLEEPTIME", "36"),
	HEARTBEATFRE_COMMAND("HEARTBEATFRE", "48"),
	SOS_COMMAND("SOS", "50"),
	GENERALREPLY_COMMAND("GENERALREPLY", "54"),
	LOCATIONINTERVAL_COMMAND("LOCATIONINTERVAL", "52"),
	FAMILYNUMBERS_COMMAND("FAMILYNUMBERS","06"),
	TRECORD_COMMAND("TRECORD","56"),
	CHATFUNCTION_COMMAND("CHATFUNCTION","57"),
	CLOSE_RESTART("CLOSERESTART","32"),
	TRECORD_RESP_COMMAND("TRECORDRESP", "59");

	private String type;
	private String code;
	
	private CommandType(String type, String code) {
		this.type = type;
		this.code = code;
	}
	
	static CommandType mkCommandType(String encoded) {
		for (CommandType ct : CommandType.values()) {
			if (encoded.equals(ct.code))
				return ct;
		}
		
		return null;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getCode() {
		return this.code;
	}
	
	void write(ByteBuf bout) throws Exception {
		bout.writeBytes(this.code.getBytes());
	}
}
