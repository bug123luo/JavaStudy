/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  TestMain.java   
 * @Package com.tct.junit5Sample   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月13日 上午11:33:38   
 * @Version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.junit5Sample;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**   
 * @ClassName:  TestMain   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月13日 上午11:33:38   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Retention(RetentionPolicy.RUNTIME)
@interface Version{
	int major();
	int minor();
}

@Version(major=1, minor=0)
class AccessAnnotation{
	@Version(major=1, minor=2)
	public void testMethod1() {
		
	}
	
	
	@Version(major=1, minor=1)
	@Deprecated
	public void testMethod2() {
		
	}
}


public class TestMain {

	public static void main(String[] args) {
		Class<AccessAnnotation> c= AccessAnnotation.class;
		System.out.println("Annotations for class:"+c.getName());
		printAnnotations(c);
		
		System.out.println("Method annotations:");
		Method[] m=c.getDeclaredMethods();
		for(int i=0; i<m.length; i++) {
			System.out.println("Annotations for method:"+m[i].getName());
			printAnnotations(m[i]);
		}
	}
	
	public static void printAnnotations(AnnotatedElement programElement) {
		Annotation[] annList = programElement.getAnnotations();
		for(int i=0; i< annList.length; i++) {
			System.out.println(annList[i]);
			if(annList[i] instanceof Version) {
				Version v=(Version) annList[i];
				int major = v.major();
				int minor = v.minor();
				System.out.println("Found Version annotation: "+"major ="+major+", minor="+minor);
			}
			
		}
	}
	
}
