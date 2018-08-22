package com.ubo.common.terminal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RecordMessageBeanNew {

	private String imei;
	private String recordType;
	private String isCompress;
	private int totalPackage;
	private Boolean isFinished;
	private Long lastPackageReceiveTime;
	private Map<Integer, byte[]> dataMap = new HashMap<Integer, byte[]>();
	private Map<Integer, Integer> unreceivePackageNoMap = new HashMap<Integer, Integer>();
	private int totalLen;
	private String token;

	public RecordMessageBeanNew(String imei, 
				  String recordType, 
				  String isCompress, 
				  byte[] data, 
				  int totalPackage,
				  int packageNo,
				  String token) {
		
		this.imei = imei;
		this.recordType = recordType;
		this.isCompress = isCompress;
		this.dataMap.put(packageNo, data);
		this.totalLen = data.length;
		this.totalPackage = totalPackage;
		this.lastPackageReceiveTime = System.currentTimeMillis();
		this.isFinished = checkIsFinished();
		this.token = token;
		initUnreceivePackageNoMap(totalPackage);
	}
	
	public synchronized void addData(byte[] data, int packageNo) {
		this.dataMap.put(packageNo, data);
		this.totalLen += data.length;
		this.lastPackageReceiveTime = System.currentTimeMillis();
		this.isFinished = checkIsFinished();
	}
	
	public void initUnreceivePackageNoMap(int totalPackage) {
		for (int i=1; i<=totalPackage; i++) {
			unreceivePackageNoMap.put(i, i);
		}
	}
	
	// 检查语音是否已经接收完毕 删除已经收到的语音包号
	public boolean checkIsFinished() {
		boolean b = true;
		Set<Integer> keys = this.dataMap.keySet();
		
		for (int i=1; i<=this.totalPackage; i++) {
			if (!keys.contains(i)) {
				b = false;
			} else {
				unreceivePackageNoMap.remove(i);
			}
		}
		
		return b;
	}
	
	// fileName = imei+lastPackageReceiveTime + .amr
	public String saveToFile(String saveDir) throws IOException {
		// 注意去掉校验位
		byte[] dest = new byte[totalLen-totalPackage];
		
		int index = 0;
		for(int i=1; i<=this.totalPackage; i++) {
			byte[] src = dataMap.get(i);
			System.arraycopy(src, 0, dest, index, src.length-1);
			index += src.length-1;
		}
		
		String fileName = this.imei + "_" + this.lastPackageReceiveTime + ".amr";
		String filePath = saveDir + fileName; 
		
		File dir = new File(saveDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		File saveFile = new File(filePath);
		if (!saveFile.exists())
			saveFile.createNewFile();
		
		FileOutputStream fos = new FileOutputStream(filePath, true);
		fos.write(dest);
		fos.flush();
		fos.close();
		
		//清除缓存
		//this.dataMap.clear();
		
		return fileName;
	}
	
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getIsCompress() {
		return isCompress;
	}

	public void setIsCompress(String isCompress) {
		this.isCompress = isCompress;
	}

	public int getTotalPackage() {
		return totalPackage;
	}

	public void setTotalPackage(int totalPackage) {
		this.totalPackage = totalPackage;
	}

	public Boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Long getLastPackageReceiveTime() {
		return lastPackageReceiveTime;
	}

	public void setLastPackageReceiveTime(Long lastPackageReceiveTime) {
		this.lastPackageReceiveTime = lastPackageReceiveTime;
	}

	public int getTotalLen() {
		return totalLen;
	}

	public void setTotalLen(int totalLen) {
		this.totalLen = totalLen;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Map<Integer, Integer> getUnreceivePackageNoMap() {
		return unreceivePackageNoMap;
	}

	// test
	public static void main(String[] args) throws IOException {
		String filePath = "d:/test.txt";
		File file = new File(filePath);
		
		byte[] dest = {0,1,2,3,4,5,6,7,8,9};
		
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		
		FileOutputStream fos = new FileOutputStream(filePath, true);
		fos.write(dest);
		fos.flush();
		fos.close();
		
		FileInputStream fis = new FileInputStream("D:/record/888888888888888_1453105895222.amr");
		byte[] buf = new byte[64];
		int len = 0;
		int sum = 0;
		while ((len = fis.read(buf)) != -1) {
			for (int i=0; i<len; i++) {
				System.out.println(buf[i]);
				++sum;
			}
		}
		
		fis.close();
		
		System.out.println(sum);
	}

}
