package com.ubo.common.terminal;

import dudu.service.core.terminal.BasicMessage;

public class UboRecordMessage extends BasicMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1325331428849940262L;

	private Boolean zip;
	private String zipMethod;
	private String recordType;
	private int totalPkgNo;
	private int pkgNo;
	private int length;
	private byte[] data;
	private String token;
	/**
	 * 新增此字段用来存储redis缓存的key
	 */
	private String recordId;
	/**
	 * 增加此字段来保存文件存储路径
	 */
	private String fileName;
	public UboRecordMessage() {
		
	}

	public Boolean getZip() {
		return zip;
	}

	public void setZip(Boolean zip) {
		this.zip = zip;
	}

	public String getZipMethod() {
		return zipMethod;
	}

	public void setZipMethod(String zipMethod) {
		this.zipMethod = zipMethod;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public int getTotalPkgNo() {
		return totalPkgNo;
	}

	public void setTotalPkgNo(int totalPkgNo) {
		this.totalPkgNo = totalPkgNo;
	}

	public int getPkgNo() {
		return pkgNo;
	}

	public void setPkgNo(int pkgNo) {
		this.pkgNo = pkgNo;
	} 
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	} 
	
}
