/*
 * Copyright 2015 The Dudu Project
 *
 * The Dudu Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package dudu.service.dao;

import java.util.Iterator;
import java.util.List;

import dudu.service.dao.bean.OrderBean;

public class OrderDao extends DuduDaoSupport {
	

	public OrderDao() {
		
	}
	/**
	 * 根据订单id获取订单信息
	 * @param orderId
	 * @return
	 * @throws DaoException
	 */
	public OrderBean getOrder(long orderId)
		throws DaoException {
		
		String hql = String.format(
			"FROM OrderBean ab WHERE ab.orderId==%s",
			orderId);
		
		return (OrderBean)super.uniqueResult(hql);
	}
	/**
	 * 获取用户已付款的新订单信息
	 * @return
	 * @throws DaoException 
	 */
	public List<OrderBean> getNewPayedOrders() throws DaoException{
		//先把未读状态为空的且已经支付成功的订单信息置为未读
		super.update("UPDATE OrderBean ab set ab.hasRead=0 where ab.hasRead is null and (ab.referer like '%#%')");
		String hql = "FROM OrderBean ab WHERE (ab.hasRead is null or ab.hasRead=0) and (ab.referer like '%#%') and (ab.payStatus=1)";	
		//获取新订单
		List<OrderBean> orders=super.query(hql);
		StringBuffer buffer=new StringBuffer();
		Iterator<OrderBean> iterator=orders.iterator();
		while(iterator.hasNext()){
			buffer.append(String.valueOf(iterator.next().getOrderId()));
			if(iterator.hasNext()){
				buffer.append(',');
			}			
		}
		if(buffer.length()>0){
			String updateSql="UPDATE OrderBean e set e.hasRead=1 WHERE e.orderId in ("+
			buffer.toString()+")";
			//修改订单已读状态
			super.update(updateSql);
		}
		
		return orders;
	}
	/**
	 * 根据用户手机号获取用户订单信息
	 * @param mobile
	 * @return
	 * @throws DaoException
	 */
	public List<OrderBean> getOrdersByPhone(String mobile) throws DaoException{
		String hql=String.format(
				"FROM OrderBean ab WHERE ab.mobile=\'%s\' order by ab.payTime desc ",
				mobile);
		return super.query(hql);
	}
	public static void main(String[] args) throws DaoException {
		new OrderDao().getOrdersBySensorId("123444333");
	}
	
	/**
	 * 根据友娃卡IMEI号获取订单信息
	 * @param sensorId
	 * @return
	 * @throws DaoException
	 */
	public List<OrderBean> getOrdersBySensorId(String sensorId) throws DaoException{
		String hql=String.format(
				"FROM OrderBean ab WHERE ab.referer like \'%%%s#%%\' order by ab.payTime desc ",
				sensorId);
		//System.out.println(hql);
		//return null;
		return super.query(hql);
	}
	
}
