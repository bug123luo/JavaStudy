/**
 * 用于缓存设备电量信息
 * @author danyuan
 * @date 2016-07-20
 */
package dudu.service.common;

import dudu.service.dto.BatteryStatusBean;

public interface BatteryService {
	public Boolean saveBatteryStatus(BatteryStatusBean batteryMessage)throws BusinessException;
	public BatteryStatusBean loadBatteryStatus(String sensorId)throws BusinessException;
}
