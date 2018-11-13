package com.tct.junit5Sample;

import java.lang.annotation.Annotation;

/**
 * 
 * @ClassName:  App   
 * @Description:打印某个类的所有注释类型   
 * @author: 泰源云景
 * @date:   2018年11月13日 上午11:20:25   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@SuppressWarnings("unchecked")
@Deprecated
public class App 
{
	
	  public static void main(String[] argv) {
		    // Get the class object reference
		    Class<App> c = App.class;
		    // Get all annotations on the class declaration
		    Annotation[] allAnns = c.getAnnotations();
		    System.out.println("Annotation count: " + allAnns.length);

		    // Print all annotations
		    for (Annotation ann : allAnns) {
		      System.out.println(ann);
		    }
		  }
}
