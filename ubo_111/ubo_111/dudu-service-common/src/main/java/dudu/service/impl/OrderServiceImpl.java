package dudu.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.common.BusinessErrorConst;
import dudu.service.common.BusinessException;
import dudu.service.common.OrderService;
import dudu.service.dao.DaoException;
import dudu.service.dao.OrderDao;
import dudu.service.dao.bean.AccountBean;
import dudu.service.dao.bean.OrderBean;

public class OrderServiceImpl implements OrderService{
	private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	private OrderDao orderDao;
	
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public OrderBean getOrder(long orderId) throws BusinessException {
		 logger.debug(".getOrder(orderId={})", orderId);
	        try {
	        	return orderDao.getOrder(orderId);	           
	        } catch (DaoException daoException) {
	            throw new BusinessException(
	                    BusinessErrorConst.DaoException,
	                    daoException.getMessage(),
	                    daoException.getCause());
	        }
	}

	@Override
	public List<OrderBean> getNewPayedOrders() throws BusinessException {
		 logger.debug(".getNewPayedOrders()");
	        try {
	        	return orderDao.getNewPayedOrders();	           
	        } catch (DaoException daoException) {
	            throw new BusinessException(
	                    BusinessErrorConst.DaoException,
	                    daoException.getMessage(),
	                    daoException.getCause());
	        }
	}

	@Override
	public List<OrderBean> getOrdersByPhone(String mobile) throws BusinessException {
		logger.debug(".getOrdersByPhone(mobile={})", mobile);
        try {
        	return orderDao.getOrdersByPhone(mobile);	           
        } catch (DaoException daoException) {
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }
	}

	@Override
	public List<OrderBean> getOrdersBySensorId(String sensorId) throws BusinessException {
		logger.debug(".getOrdersBySensorId(sensorId={})", sensorId);
        try {
        	return orderDao.getOrdersBySensorId(sensorId);	           
        } catch (DaoException daoException) {
            throw new BusinessException(
                    BusinessErrorConst.DaoException,
                    daoException.getMessage(),
                    daoException.getCause());
        }
	}

}
