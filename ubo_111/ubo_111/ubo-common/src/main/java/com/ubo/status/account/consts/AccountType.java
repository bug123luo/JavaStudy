package com.ubo.status.account.consts;

public class AccountType {
	/**
	 * 未知类别
	 */
	public static final int TYPE_UNKNOW=0;
	/**
	 * 1、捐赠、测试用机：卡、年费全免；
	 */
	public static final int TYPE_DONATE=1;
	/**
	 * 2、赞助：仅送卡，费用照收；
	 */
	public static final int TYPE_SUPPORT=2;
	/**
	 * 3、送机（学校等）：送卡，需缴年费后（线下代收，再发卡），方可开通提供服务；
	 */
	public static final int TYPE_PRESENT_CARD=3;
	/**
	 * 4、购机：有一定试用期，按月/季/年方式缴费，试用期一到，如未缴费，则停止服务；
	 */
	public static final int TYPE_BUY_CARD=4;
	/**
	 * 5、租赁：交押金200元，按/月/季/年缴通讯服务费；
	 */
	public static final int TYPE_RENT=5;	
	/**
	 * 6、深港卡：有一定试用期，按月/季/年方式缴费，试用期一到，如未缴费，则停止服务；
	 */
	public static final int TYPE_SG_CARD=6;
}
