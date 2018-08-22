package dudu.service.common;

import dudu.service.core.MessageException;

public interface RequestLocationAction {
	void requestLocation(String sessionToken,
		String sensorId) throws MessageException;
}
