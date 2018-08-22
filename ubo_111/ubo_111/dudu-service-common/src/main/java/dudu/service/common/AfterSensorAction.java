package dudu.service.common;

import dudu.service.core.MessageException;

public interface AfterSensorAction {
	
	public void afterSleepTimeSet(
		String sessionToken,
		String sensorId,
		String sleepTime) throws MessageException;
	
	// 设置心跳频率
	public void afterHeartbeatFreSet(
		String sessionToken,
		String sensorId,
		String fre) throws MessageException;
	
	// 设置定位模式+上传频率
	public void afterLocationPatFreSet(
		String sessionToken,
		String sensorId,
		String pat,
		String fre) throws MessageException;
	
	// 设置定位时段
	public void afterLocationIntervalSet(
		String sessionToken,
		String sensorId,
		String locationInterval
		) throws MessageException;
	
	// 设置亲情号码
	public void afterFamilyNumbersSet(
		String sessionToken,
		String sensorId,
		String familyNumbers) throws MessageException; 
	
	// 重启和开关机
	public void colseOrRestart(
		String sessionToken,
		String sensorId,
		String command) throws MessageException; 
			
}
