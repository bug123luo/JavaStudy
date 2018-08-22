package com.ubo.common.terminal;

public class DownRecordMessageHolder {
	
	private UboCommandMessageEx command;
	private long lastSendTime;
	private int sendTimes;
	
	public DownRecordMessageHolder(UboCommandMessageEx command, long lastSendTime, int sendTimes) {
		super();
		this.command = command;
		this.lastSendTime = lastSendTime;
		this.sendTimes = sendTimes;
	}

	public UboCommandMessageEx getCommand() {
		return command;
	}

	public void setCommand(UboCommandMessageEx command) {
		this.command = command;
	}

	public long getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(long lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public int getSendTimes() {
		return sendTimes;
	}

	public void setSendTimes(int sendTimes) {
		this.sendTimes = sendTimes;
	}

}
