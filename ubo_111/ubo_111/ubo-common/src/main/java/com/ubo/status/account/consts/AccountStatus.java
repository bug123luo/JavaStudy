package com.ubo.status.account.consts;

public class AccountStatus {
	/**
	 * 未开启收费功能
	 */
	public static final int STATUS_FREE=0;
	/**
	 * 试用期状态
	 */
	public static final int STATUS_TRIAL=1;
	/**
	 * 在缴费服务期内
	 */
	public static final int STATUS_WORKING=2;	
	/**
	 * 欠费0-7天
	 */
	public static final int STATUS_NO=3;
	/**
	 * 欠费7-14天
	 */
	public static final int STATUS_NO_FEE=4;
	/**
	 * 欠费14天以上
	 */
	public static final int STATUS_NO_FEE_LONG=5;
	
	
}
