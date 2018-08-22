package dudu.service.test;

import java.util.Date;
import java.util.List;

import dudu.service.common.BusinessException;
import dudu.service.dao.DaoException;
import dudu.service.test.dao.bean.TrackBean;

public interface TrackerService {
	
	public List<TrackBean> get(long devId, Date start, Date end) 
		throws BusinessException, DaoException;
}
