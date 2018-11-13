/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  TestMain1.java   
 * @Package com.tct.junit5Sample   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年11月13日 上午11:50:59   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.junit5Sample;

/**   
 * @ClassName:  TestMain1   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年11月13日 上午11:50:59   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@interface LogHistory {
  Log[] value();
}
@Repeatable(LogHistory.class)
@interface Log {
  String date();
  String comments();
}

@Log(date = "02/01/2014", comments = "A")
@Log(date = "01/22/2014", comments = "B")
public class TestMain1 {
  public static void main(String[] args) {
    Class<TestMain1> mainClass = TestMain1.class;

    Log[] annList = mainClass.getAnnotationsByType(Log.class);
    for (Log log : annList) {
      System.out.println("Date=" + log.date() + ", Comments=" + log.comments());
    }

    Class<LogHistory> containingAnnClass = LogHistory.class;
    LogHistory logs = mainClass.getAnnotation(containingAnnClass);
 
    for (Log log : logs.value()) {
      System.out.println("Date=" + log.date() + ", Comments=" + log.comments());
    }
  }
}
