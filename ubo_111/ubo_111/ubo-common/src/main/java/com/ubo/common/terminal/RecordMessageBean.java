package com.ubo.common.terminal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordMessageBean {

	private String imei;
	private String recordType;
	private String isCompress;
	private int totalPackage;
	private Boolean isFinished;
	private Long lastPackageReceiveTime;
	private byte[] checkArray;
	private List<byte[]> datas = new ArrayList<byte[]>();
	private int totalLen;
	private String token;

	public RecordMessageBean(String imei, 
				  String recordType, 
				  String isCompress, 
				  byte[] data, 
				  int totalPackage,
				  int index,
				  String token) {
		
		this.imei = imei;
		this.recordType = recordType;
		this.isCompress = isCompress;
		this.datas.add(index,data);
		this.totalLen = data.length;
		this.totalPackage = totalPackage;
		initCheckArray(totalPackage, index);
		this.lastPackageReceiveTime = System.currentTimeMillis();
		this.isFinished = checkIsFinished();
		this.token = token;
	}
	
	private void initCheckArray(int size, int index) {
		this.checkArray = new byte[size];
		for(int i=0; i<size; i++) {
			this.checkArray[i] = 0;
		}
		this.checkArray[index] = 1;
	}
	
	public synchronized void addData(byte[] data, int index) {
		this.datas.add(index, data);
		this.totalLen += data.length;
		this.lastPackageReceiveTime = System.currentTimeMillis();
		this.checkArray[index] = 1;
		this.isFinished = checkIsFinished();
	}
	
	public boolean checkIsFinished() {
		boolean b = true;
		for (int i=0; i<this.totalPackage; i++) {
			if (this.checkArray[i] == 0) {
				b = false;
				break;
			}
		}
		return b;
	}
	
	// fileName = imei+lastPackageReceiveTime + .amr
	public String saveToFile(String saveDir) throws IOException {
		// 注意去掉校验位
		byte[] dest = new byte[totalLen-totalPackage];
		
		int index = 0;
		for(int i=0; i<datas.size(); i++) {
			byte[] src = datas.get(i);
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

	public List<byte[]> getDatas() {
		return datas;
	}

	public void setDatas(List<byte[]> datas) {
		this.datas = datas;
	}


	public byte[] getCheckArray() {
		return checkArray;
	}

	public void setCheckArray(byte[] checkArray) {
		this.checkArray = checkArray;
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
