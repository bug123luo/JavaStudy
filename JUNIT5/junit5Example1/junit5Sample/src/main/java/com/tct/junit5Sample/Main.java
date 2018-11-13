/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  Main.java   
 * @Package com.tct.junit5Sample   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月13日 上午11:21:07   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.junit5Sample;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
/**   
 * @ClassName:  Main   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月13日 上午11:21:07   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Version{
	int major();
	
	int minor();
}

@Version(major=1,minor=2)
public class Main {
	public static void main(String[] args) {
		Class<Main> c = Main.class;
		
		Version v = c.getAnnotation(Version.class);
		
		if(v==null) {
			System.out.println("Version annotation is not present.");
		}else {
			int major = v.major();
			int minor = v.minor();
			System.out.println("Version: major="+major+",minor="+minor);
		}
	}
}
