package com.ubo.status.account.consts;

public class PermissionLevel {
	/**
	 * 正常工作状态，不做任何限制
	 */
	public static final int PERMISSION_WORKING=0;
	/**
	 * 欠费7天内
	 */
	public static final int PERMISSION_NO=1;
	/**
	 * 欠费7-14天内
	 */
	public static final int PERMISSION_NO_FEE=2;
	/**
	 * 欠费14天以上
	 */
	public static final int PERMISSION_NO_FEE_LONG=3;
	/**
	 * 不做任何限制
	 */
	public static final int PERMISSION_ALL=66666;
	/**
	 * 根据标识字符串转义为状态等级
	 * @param flag
	 * @return
	 */
	public static int level(String flag){
		
		int status=PERMISSION_ALL;
		if(flag==null){
			return status;
		}
		if(flag.equals("PERMISSION_WORKING")){
			status=PERMISSION_WORKING;
		}		
		if(flag.equals("PERMISSION_NO")){
			status=PERMISSION_NO;
		}
		if(flag.equals("PERMISSION_NO_FEE")){
			status=PERMISSION_NO_FEE;
		}
		if(flag.equals("PERMISSION_NO_FEE_LONG")){
			status=PERMISSION_NO_FEE_LONG;
		}	
		if(flag.equals("PERMISSION_ALL")){
			status=PERMISSION_ALL;
		}
		return status;
	}
	/**
	 * 根据状态值确定权限等级
	 */
	public static int level(int status){
		int level=status;
		switch(status){
		case AccountStatus.STATUS_FREE:
			level=PERMISSION_WORKING;
			break;
		case AccountStatus.STATUS_TRIAL:
			level=PERMISSION_WORKING;
			break;
		case AccountStatus.STATUS_WORKING:
			level=PERMISSION_WORKING;
			break;
		case AccountStatus.STATUS_NO:
			level=PERMISSION_NO;
			break;
		case AccountStatus.STATUS_NO_FEE:
			level=PERMISSION_NO_FEE;
			break;
		case AccountStatus.STATUS_NO_FEE_LONG:
			level=PERMISSION_NO_FEE_LONG;
			break;
		default:;
		}
		return level;
	}
}
