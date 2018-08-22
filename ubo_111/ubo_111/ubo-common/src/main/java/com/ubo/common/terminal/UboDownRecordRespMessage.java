/**
 * 
 */
package com.ubo.common.terminal;

import dudu.service.core.terminal.BasicMessage;

/**
 * @author mrgdren
 *
 */
public class UboDownRecordRespMessage extends BasicMessage {

	private static final long serialVersionUID = 5481832928807965886L;
	public static final String RESULT_OK   = "0";
	public static final String RESULT_FAIL = "1";
	
	private String totalPackageNo;
	private String packageNumber;
	private String result;
	private String spaceStatus;
	
	public UboDownRecordRespMessage() {
		
	}
	
	public String getTotalPackageNo() {
		return totalPackageNo;
	}

	public void setTotalPackageNo(String totalPackageNo) {
		this.totalPackageNo = totalPackageNo;
	}

	public String getPackageNumber() {
		return packageNumber;
	}

	public void setPackageNumber(String packageNumber) {
		this.packageNumber = packageNumber;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSpaceStatus() {
		return spaceStatus;
	}

	public void setSpaceStatus(String spaceStatus) {
		this.spaceStatus = spaceStatus;
	}

}
