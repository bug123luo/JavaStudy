/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  WebConfig.java   
 * @Package com.lcclovehww.springboot.chapter12.config   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年2月14日 下午4:02:56   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.springboot.chapter12.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**   
 * @ClassName:  WebConfig   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年2月14日 下午4:02:56   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Configurable
@EnableWebSecurity//如果缺少这个，页面不能正常跳转
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 使得"/login/page"映射为"login.jsp"
		registry.addViewController("/login/page").setViewName("login");
		// 使得"/logout/page"映射为"logout_welcome.jsp"
		registry.addViewController("/logout/page").setViewName("logout_welcome");
		// 使得"/logout"映射为"logout.jsp"
		registry.addViewController("/logout").setViewName("logout");
	}
}
