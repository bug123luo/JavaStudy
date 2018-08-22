package dudu.service.dao.bean;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 商城订单信息
 * @author danyuan
 *
 */
public class OrderBean implements Serializable {
	private static final Logger LOG=LoggerFactory.getLogger(OrderBean.class);
	private Long orderId;
	private String mobile;//电话号码
	private Boolean payStatus;//支付状态
	private Integer orderStatus;//订单状态
	private Integer payId;//支付方式id
	private String payName;//支付方式名称
	private Float payPrice;//支付金额
	private String referer;//套餐详情：353848049236792#30(imei#天数)
	private Boolean hasRead;//是否已读
	private Long payTime;//支付时间
	//dto
	private String sensorId;
	private Integer days;
	
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Boolean isPayStatus() {
		return payStatus;
	}
	public Boolean getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(byte payStatus) {
		this.payStatus = payStatus==1;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getPayId() {
		return payId;
	}
	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public Float getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(Float payPrice) {
		this.payPrice = payPrice;
	}
	public String getReferer() {
		
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
		if(referer==null||(referer.trim().equals(""))){
			return;
		}
		String args[]=referer.split("#");
		this.setSensorId(args[0]);
		try{
			this.setDays(Integer.parseInt(args[1]));
		}catch(NumberFormatException e){
			LOG.debug("orderBean : invalid day count !");
		}
	}
	public Boolean isHasRead() {
		return hasRead;
	}
	public Boolean getHasRead() {
		return hasRead;
	}
	public void setHasRead(byte hasRead) {
		this.hasRead = hasRead==1;
	}
	public Long getPayTime() {
		return payTime;
	}
	public void setPayTime(Long payTime) {
		this.payTime = payTime;
	}
	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
