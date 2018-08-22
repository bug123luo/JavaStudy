package dudu.service.test;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.common.BusinessException;
import dudu.service.dao.DaoException;
import dudu.service.test.dao.TrackDao;
import dudu.service.test.dao.bean.TrackBean;

public class TrackerServiceImpl implements TrackerService {
	
	private static Logger logger = 
		LoggerFactory.getLogger(TrackerServiceImpl.class);
	
	private TrackDao trackDao;
	
	public TrackerServiceImpl() {
		
	}
	
	public TrackDao getTrackDao() {
		return trackDao;
	}

	public void setTrackDao(TrackDao trackDao) {
		this.trackDao = trackDao;
	}
	
	public List<TrackBean> get(long devId, Date start, Date end)
		throws BusinessException, DaoException {
		
		logger.debug(".get(devId={}, start={}, end={})",
			new Object[] {devId, start, end});
		
		return trackDao.get(devId, start, end);
	}

}
