package com.ubo.common.terminal;

import java.util.List;

import dudu.service.core.Tuple2;
import dudu.service.core.terminal.BasicMessage;

public class UboGuardAreaAckMessage extends BasicMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2109616800055712486L;
	private List<Tuple2<String, Boolean>> ackList;
	
	public UboGuardAreaAckMessage() {
		
	}

	public List<Tuple2<String, Boolean>> getAckList() {
		return ackList;
	}

	public void setAckList(List<Tuple2<String, Boolean>> ackList) {
		this.ackList = ackList;
	}
	
}
