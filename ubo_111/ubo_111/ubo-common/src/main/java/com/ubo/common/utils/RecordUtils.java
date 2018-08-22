package com.ubo.common.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.common.terminal.CommandType;
import com.ubo.common.terminal.UboCommandMessageEx;

public class RecordUtils {
	private static final Logger LOG=
			LoggerFactory.getLogger(RecordUtils.class);
	private static final String SAVE_FILE_DIR = "/home/upload_record/";
	
	public static UboCommandMessageEx getCommand(String fileName, String sensorId, String msgId) throws IOException {
		return getCommand(SAVE_FILE_DIR,fileName,sensorId,msgId);
	}

	public static UboCommandMessageEx getCommand(String saveFileDir,String fileName, String sensorId, String msgId) throws IOException {
		FileInputStream fis = null;
		byte[] b = new byte[1024];
		int len = 0;
		int offset = 0;

		byte[] data = null;
		int dataLength = 0;

		if (fileName != null) {
			try {
				if(saveFileDir==null||saveFileDir.trim().equals("")){
					saveFileDir=SAVE_FILE_DIR;
				}
				
				fis = new FileInputStream(saveFileDir + fileName);
				LOG.debug(saveFileDir + fileName);
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

		UboCommandMessageEx command = new UboCommandMessageEx(sensorId, CommandType.TRECORD_COMMAND, null);
		command.setMsgId(msgId);
		command.setType(CommandType.TRECORD_COMMAND.getType());
		command.setTotalPkgNO(1);
		command.setPkgNO(1);
		command.setZip("0");
		byte[] zipBody = new byte[dataLength + 1];
		System.arraycopy(data, 0, zipBody, 0, dataLength);
		zipBody[dataLength] = getCheckSum(zipBody);
		command.setZipLength(dataLength + 1);
		command.setZipBody(zipBody);

		return command;

	}

	private static byte getCheckSum(byte[] data) {
		byte sum = 0;
		for (int i = 0; i < data.length - 1; i++) {
			sum += data[i];
		}
		return sum;
	}

}
