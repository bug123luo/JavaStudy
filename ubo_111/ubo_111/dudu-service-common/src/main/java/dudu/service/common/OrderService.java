package dudu.service.common;

import java.util.List;

import dudu.service.dao.bean.OrderBean;

public interface OrderService {
	/**
	 * 根据订单id获取订单信息
	 * @param orderId
	 * @return
	 * @throws BusinessException
	 */
	public OrderBean getOrder(long orderId)throws BusinessException;
	/**
	 * 获取用户已付款的新订单信息
	 * @return
	 * @throws BusinessException
	 */
	public List<OrderBean> getNewPayedOrders()throws BusinessException;
	/**
	 * 根据用户手机号获取用户订单信息
	 * @param mobile
	 * @return
	 * @throws BusinessException
	 */
	public List<OrderBean> getOrdersByPhone(String mobile)throws BusinessException;
	/**
	 * 根据友娃卡IMEI号获取订单信息
	 * @param sensorId
	 * @return
	 * @throws BusinessException
	 */
	public List<OrderBean> getOrdersBySensorId(String sensorId)throws BusinessException;
}
