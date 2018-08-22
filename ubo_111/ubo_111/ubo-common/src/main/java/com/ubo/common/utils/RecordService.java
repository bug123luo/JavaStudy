package com.ubo.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ubo.common.terminal.UboCommandMessageEx;

public class RecordService {

	public List<UboCommandMessageEx> commands = new ArrayList<UboCommandMessageEx>();

	private static final String SAVE_FILE_DIR = "/home/upload_record/";
	@SuppressWarnings("unused")
	private static final int SPLIT_PACKAGE_LENGTH = 1024 * 60; // 60k 1分钟语音

	private String fileName;
	@SuppressWarnings("unused")
	private String sensorId;
	private String msgId;

	@SuppressWarnings("unused")
	private int totalPkgNO;

	private byte[] data;
	private int dataLength;

	public RecordService(String fileName, String sensorId) {
		this.fileName = fileName;
		this.sensorId = sensorId;
		this.msgId = generateMsgId();
	}
	
	public int getDataLength() {
		return dataLength;
	}
	
	public String getMsgId() {
		return msgId;
	}

	public void split() throws IOException {
		FileInputStream fis = null;
		byte[] b = new byte[1024];
		int len = 0;
		int offset = 0;
		
		if (fileName != null) {
			try {
				fis = new FileInputStream(SAVE_FILE_DIR + fileName);
				dataLength = fis.available();
				data = new byte[dataLength];
				while ((len = fis.read(b)) != -1) {
					System.arraycopy(b, 0, data, offset, len);
					offset += len;
				}
			} finally {
				fis.close();
			}
		}
		
		/**************************不在这边组包*******************************
		// 计算分包
		int flag = dataLength % SPLIT_PACKAGE_LENGTH;
		
		if (dataLength <= SPLIT_PACKAGE_LENGTH) {
			totalPkgNO = 1;
		} else  {
			totalPkgNO = dataLength / SPLIT_PACKAGE_LENGTH;			
		}
		
		UboCommandMessageEx command = null;
		offset = 0;
		byte[] zipBody = null;
		
		if (totalPkgNO == 1) {
			command = new UboCommandMessageEx(sensorId, CommandType.TRECORD_COMMAND, null);
			command.setMsgId(msgId);
			command.setType(CommandType.TRECORD_COMMAND.getType());
			command.setTotalPkgNO(totalPkgNO);
			command.setPkgNO(1);
			command.setZip("0");
			zipBody = new byte[dataLength + 1];
			System.arraycopy(data, 0, zipBody, 0, dataLength);
			zipBody[dataLength] = getCheckSum(zipBody);
			command.setZipLength(dataLength+1);
			command.setZipBody(zipBody);
			commands.add(command);
		} else {
			for (int i=1; i<=totalPkgNO; i++) {
				command = new UboCommandMessageEx(sensorId, CommandType.TRECORD_COMMAND, null);
				command.setMsgId(msgId);
				command.setType(CommandType.TRECORD_COMMAND.getType());
				command.setTotalPkgNO(totalPkgNO);
				command.setPkgNO(i);
				command.setZip("0");
				
				if (i == totalPkgNO && flag != 0) {
					zipBody = new byte[dataLength-offset+1];
					System.arraycopy(data, offset, zipBody, 0, dataLength-offset);
					zipBody[dataLength-offset] = getCheckSum(zipBody);
					command.setZipLength(dataLength-offset+1);
					command.setZipBody(zipBody);
				} else {
					zipBody = new byte[SPLIT_PACKAGE_LENGTH + 1];
					System.arraycopy(data, offset, zipBody, 0, SPLIT_PACKAGE_LENGTH);
					zipBody[SPLIT_PACKAGE_LENGTH] = getCheckSum(zipBody);
					command.setZipLength(SPLIT_PACKAGE_LENGTH+1);
					command.setZipBody(zipBody);
					offset += SPLIT_PACKAGE_LENGTH;
				}
				
				commands.add(command);
	
			}
		}
		
		*************************************************************************/
		
	}
	
	public byte getCheckSum(byte[] data) {
		byte sum = 0;
		for (int i=0; i<data.length-1; i++) {
			sum += data[i];
		}
		return sum;
	}

	public static String generateMsgId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return String.format("%s%04d", sdf.format(new Date()),1);
	}
	
}
