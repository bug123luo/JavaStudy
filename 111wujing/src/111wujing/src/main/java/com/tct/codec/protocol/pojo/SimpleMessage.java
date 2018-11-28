package com.tct.codec.protocol.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class SimpleMessage implements Serializable{
	

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 4110618336925236368L;
	
	private String uniqueIdentification;
	private String formatVersion;
	private Integer deviceType;
	private String serialNumber;
	private String messageType;
	private String sendTime;
	private String sessionToken;
		
}
