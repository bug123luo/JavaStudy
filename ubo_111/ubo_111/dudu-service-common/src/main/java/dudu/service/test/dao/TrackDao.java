package dudu.service.test.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dudu.service.dao.DaoException;
import dudu.service.dao.DuduDaoSupport;
import dudu.service.test.dao.bean.TrackBean;

public class TrackDao extends DuduDaoSupport {
	
	private SimpleDateFormat timeFormat;

	public TrackDao() {
		
	}
	
	public SimpleDateFormat getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(SimpleDateFormat timeFormat) {
		this.timeFormat = timeFormat;
	}

	public List<TrackBean> get(long trackDev, Date startTime, Date endTime)
		throws DaoException {
		
		String hql = String.format(
			"From TrackBean tb WHERE tb.trackDev=%d AND " + 
			"(tb.time BETWEEN '%s' AND '%s') ORDER BY tb.time ASC",
			trackDev,
			timeFormat.format(startTime),
			timeFormat.format(endTime));
		
		return super.query(hql);
	}
	
}
