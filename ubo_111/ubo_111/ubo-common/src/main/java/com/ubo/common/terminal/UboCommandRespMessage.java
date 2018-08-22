/**
 * 
 */
package com.ubo.common.terminal;

import dudu.service.core.terminal.BasicMessage;

/**
 * @author mrgdren
 *
 */
public class UboCommandRespMessage extends BasicMessage {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7710834701815462014L;
	private String result;
	
	public UboCommandRespMessage() {
		
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
