package com.ubo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final int NO_INSURANCE = 0;
	public static final int ACCIDENT_INSURANCE = 1;
	public static final int TRAFFIC_ACCIDENT_INSURANCE = 2;

	// 从身份证获取年龄获取险种类型
	// 根据身份证信息可知被保险人年龄， 1:6个月-17周岁为意外伤害险 2:18周岁-90周岁为交通意外险 0: 无
	public static int getInsuranceType(String idcard) {

		IdcardInfoExtractor ie = new IdcardInfoExtractor(idcard);

		if (ie.getBirthday() == null) {
			return NO_INSURANCE;
		}

		int yy = ie.getYear();
		int mm = ie.getMonth();
		int dd = ie.getDay();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		int yy1 = calendar.get(Calendar.YEAR);
		int mm1 = calendar.get(Calendar.MONTH);
		int dd1 = calendar.get(Calendar.DAY_OF_MONTH);

		int diffYear = yy1 - yy;
		int diffMonth = mm1 - mm;
		@SuppressWarnings("unused")
		int diffDay = dd1 - dd;

		if (diffYear == 0) {
			if (diffMonth >= 6) {
				return ACCIDENT_INSURANCE;
			} else {
				return NO_INSURANCE;
			}
		}

		if (diffYear == 1) {

			if ((12 - mm) + mm1 >= 6) {
				return ACCIDENT_INSURANCE;
			} else {
				return NO_INSURANCE;
			}
		}

		if (diffYear >= 2 && diffYear <= 17) {
			return ACCIDENT_INSURANCE;
		}

		if (diffYear == 18) {
			if (diffMonth >= 0) {
				return TRAFFIC_ACCIDENT_INSURANCE;
			} else {
				return ACCIDENT_INSURANCE;
			}
		}

		if (diffYear >= 19 && diffYear <= 90) {
			return TRAFFIC_ACCIDENT_INSURANCE;
		}

		return NO_INSURANCE;

	}

	// 从身份证获取年龄获取险种类型
	// 根据身份证信息可知被保险人年龄， 1:6个月-17周岁为意外伤害险 2:18周岁-90周岁为交通意外险 0: 无
	public static int getInsuranceType(Date birthday) {

		if (birthday == null) {
			return NO_INSURANCE;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthday);

		int yy = calendar.get(Calendar.YEAR);
		int mm = calendar.get(Calendar.MONTH);
		int dd = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(new Date());

		int yy1 = calendar.get(Calendar.YEAR);
		int mm1 = calendar.get(Calendar.MONTH);
		int dd1 = calendar.get(Calendar.DAY_OF_MONTH);

		int diffYear = yy1 - yy;
		int diffMonth = mm1 - mm;
		int diffDay = dd1 - dd;
		
		System.out.println(diffYear + "," + diffMonth + "," + diffDay);

		if (diffYear == 0) {

			if (diffMonth > 6) {
				return ACCIDENT_INSURANCE;
			}

			if ((diffMonth == 6) && (diffDay >= 0)) {
				return ACCIDENT_INSURANCE;
			}

			return NO_INSURANCE;
		}

		if (diffYear == 1) {

			if ((12 - mm) + mm1 > 6) {
				return ACCIDENT_INSURANCE;
			}

			if (((12 - mm) + mm1 == 6) && diffDay >= 0) {
				return ACCIDENT_INSURANCE;
			}

			return NO_INSURANCE;
		}

		if (diffYear >= 2 && diffYear <= 17) {
			return ACCIDENT_INSURANCE;
		}

		if (diffYear == 18) {
			if (diffMonth >= 0 && diffDay >= 0) {
				return TRAFFIC_ACCIDENT_INSURANCE;
			} else {
				return ACCIDENT_INSURANCE;
			}
		}

		if (diffYear >= 19 && diffYear <= 90) {
			return TRAFFIC_ACCIDENT_INSURANCE;
		}

		return NO_INSURANCE;

	}
	/**
	 * 日期天数的加减
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException 
	 */
	public static int subDay(Date date1,Date date2) throws ParseException{
		long result=0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long date1ms=sdf.parse(sdf.format(date1)).getTime();
		long date2ms=sdf.parse(sdf.format(date2)).getTime();
		result=(date1ms-date2ms)/(24*60*60*1000);
		/*System.out.println("date12 old ms="+date1.getTime()+"  "+date2.getTime());
		System.out.println("date12 new ms="+date1ms+"  "+date2ms);
		System.out.println("result="+result);*/
		return (int)result;
	}
	public static Date subOrAddDay(Date date,int day){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return calendar.getTime();
	}
	public static void main(String[] args) throws ParseException {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(getInsuranceType(sdf.parse("2016-01-01")));
		
		System.out.println(getInsuranceType(sdf.parse("2015-09-25")));
		System.out.println(getInsuranceType(sdf.parse("2015-10-26")));
		
		System.out.println(getInsuranceType(sdf.parse("1998-02-26")));
		System.out.println(getInsuranceType(sdf.parse("1998-04-26")));
		
		System.out.println(getInsuranceType(sdf.parse("1995-09-26")));
		
		System.out.println(getInsuranceType(sdf.parse("1900-09-26")));*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateUtils.subDay(sdf.parse("2016-07-02 00:00:01"),sdf.parse("2016-07-01 23:59:59"));
		System.out.println(sdf.format(DateUtils.subOrAddDay(new Date(), -3)));
	}
}
