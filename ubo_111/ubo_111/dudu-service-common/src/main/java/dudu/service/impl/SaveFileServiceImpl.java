package dudu.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import dudu.service.common.SaveFileService;

public class SaveFileServiceImpl implements SaveFileService {
	private int threadNum=10;
	ExecutorService executor;
	public SaveFileServiceImpl(){
		executor = Executors.newFixedThreadPool(threadNum); 
	}
	public static final Logger logger=Logger.getLogger(SaveFileServiceImpl.class);
	class SaveFileThread implements Runnable{
		private String parentDir;
		private String fileName;
		private byte[] data;
		public SaveFileThread(String parentDir, String fileName, byte[] data){
			this.parentDir=parentDir;
			this.fileName=fileName;
			this.data=data;
		}
		@Override
		public void run() {
			try{
				File dir = new File(parentDir);
				if (!dir.exists()) {
					dir.mkdirs();
				}			
				File saveFile = new File(parentDir+fileName);
				if (!saveFile.exists())
					saveFile.createNewFile();
				
				FileOutputStream fos = new FileOutputStream(parentDir+fileName, true);
				fos.write(data);
				fos.flush();
				fos.close();	
			}catch(Exception e){
				logger.debug(e.getMessage());
				logger.debug(ExceptionUtils.getFullStackTrace(e));
			}				
		}
		
	}
	public int getThreadNum() {
		return threadNum;
	}
	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}
	@Override
	public void saveFile(String parentDir, String fileName, byte[] data) {		
		executor.execute(new SaveFileThread(parentDir,fileName,data));
	}

}
