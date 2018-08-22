package com.ubo.common.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.dao.bean.GuardAreaBean;

public class Utils extends dudu.service.core.utils.Utils {
	
	private static final Logger logger=LoggerFactory.getLogger(Utils.class);

	public static String generateGuardAreaMessageBody(
		String sensorId,
		List<GuardAreaBean> list,
		int limit,
		String seperator) {
		
		StringBuilder builder = new StringBuilder();
		int i = 1;
		if (list != null && !list.isEmpty()) {			
			
			for (GuardAreaBean bean : list) {
				logger.debug(
				    ".generateGuardAreaMessageBody(sensorId={},gid={},la={},lo={},r={}",
				    new Object[] {
				    sensorId,
				    bean.getGdId(),
				    bean.getLatitude(),
				    bean.getLongitude(),
				    bean.getRadius()});
				
				if (bean.getTrueId() > 0) {
					builder.append(bean.getTrueId());
				} else { // 为了兼容没有trueId的旧数据
					builder.append(i);
				}
				builder.append(seperator);
				builder.append("0");
				builder.append(seperator);
				builder.append(String.format("%.6f", bean.getLongitude()));
				builder.append("-");
				builder.append(String.format("%.6f", bean.getLatitude()));
				builder.append(seperator);
				builder.append(bean.getRadius());
				//builder.append("/");
				
				i++;
				if (i > limit) {
					break;
				} //fi
				
				builder.append("/");
			} //rof
		} else {
			builder.append("/");
		} //fi
		
		//		while (i <= limit) {
		//			builder.append(i);
		//			builder.append(seperator);
		//			builder.append("1");
		//			builder.append("/");
		//		}
		
		//builder.deleteCharAt(builder.length() - 1);
		
		return builder.toString();
	}
	
	public static byte getCheckSum(byte[] body) {
		byte checkSum = 0;
		for (int i=0; i<body.length; i++) {
			checkSum += body[i];
		}
		return checkSum;
	}
	
}
