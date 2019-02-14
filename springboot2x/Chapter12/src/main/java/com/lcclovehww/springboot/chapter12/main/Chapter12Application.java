package com.lcclovehww.springboot.chapter12.main;

//import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(scanBasePackages="com.lcclovehww.springboot.chapter12")
@MapperScan(basePackages="com.lcclovehww.springboot.chapter12", annotationClass = Mapper.class)
@EnableCaching
@Slf4j
public class Chapter12Application extends WebSecurityConfigurerAdapter{
	
	@Value("${system.user.password.secret}")
	private String secret = null;
	
	@Autowired
	UserDetailsService userDetailsService= null;

/*	@Autowired
	private DataSource dataSource = null;
	
	String pwdQuery = "select user_name, pwd, available " + "from t_user where user_name=?";
	
	String roleQuery = "select u.user_name, r.role_name"
						+" from t_user u, t_user_role ur, t_role r"
						+" where u.id = ur.user_id and r.id=ur.role_id"
						+" and u.user_name = ?";*/
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter12Application.class, args);
	}

/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//密码编码器
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//可通过passwordEncoder.encode("abc")的到加密后的密码
		String password1 = passwordEncoder.encode("abc");
		System.out.println("abc="+password1);
		//可通过passwordEncoder.encode("123456")的到加密后的密码
		String password2 = passwordEncoder.encode("123456");
		System.out.println("123456="+password2);
		//使用内存存储
		auth.inMemoryAuthentication()
			//设置密码编码器
			.passwordEncoder(passwordEncoder)
			//注册用户admin,密码为abc,并赋予USER和ADMIN角色权限
			.withUser("admin")
			.password(password1)
			.roles("USER","ADMIN")
			//连接方法
			.and()
			//注册用户myuser,密码为123456，并赋予USER的角色权限
			.withUser("myuser")
			.password(password2)
			.roles("USER");
	}*/
	
	//和上一个函数实现的方法是相同的
/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> userConfig = auth.inMemoryAuthentication().passwordEncoder(passwordEncoder);
		userConfig.withUser("admin")
				  .password(passwordEncoder.encode("abc"))
				  .authorities("ROLE_USER","ROLE_ADMIN");
		userConfig.withUser("myuser")
				  .password(passwordEncoder.encode("123456"))
				  .authorities("ROLE_USER");
	}*/
	
	//使用数据库定义的用户认证服务
/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		auth.jdbcAuthentication()
			.passwordEncoder(passwordEncoder)
			.dataSource(dataSource)
			.usersByUsernameQuery(pwdQuery)
			.authoritiesByUsernameQuery(roleQuery);
		
	}*/
	
/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(this.secret);
		
		System.out.println("password="+passwordEncoder.encode("123456"));
		auth.jdbcAuthentication()
		.passwordEncoder(passwordEncoder)
		.dataSource(dataSource)
		.usersByUsernameQuery(pwdQuery)
		.authoritiesByUsernameQuery(roleQuery);
	}*/
	
	//使用自定义的用户认证服务
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(secret);
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		
	}
	
	//authorizeRequests 方法限定只对签名成功的用户请求
	//anyRequest 方法限定所有请求
	//authenticated 方法对所有签名成功的用户允许方法
/*	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		log.debug("Using default configure(HttpSecurity)."
				+"If subclassed this will potentially "
				+"override subclass configure(HttpSecurity).");
		httpSecurity.authorizeRequests().anyRequest().authenticated()
		//formLogin 代表使用Spring Security默认的登录界面
		.and().formLogin()
		//httpBasic 方法说明启用HTTP基础认证
		.and().httpBasic();
	}*/
	
	
/*	//ANT 风格设置路径访问限制
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.
		authorizeRequests()
		//限定'/user/welcome'请求赋予角色ROLE_USER或者ROLE_ADMIN
		.antMatchers("/user/welcome","/user/details").hasAnyRole("USER","ADMIN")
		//限定'/admin/'下所有请求权限赋予角色ROLE_ADMIN
		.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
		//其他路径允许签名后访问
		.anyRequest().permitAll()
		//对于没有配置权限的其他请求允许匿名访问
		.and().anonymous()
		//使用Spring Security默认的登录页面
		.and().formLogin()
		//启动HTTP基础验证
		.and().httpBasic();
	}*/
	
	
/*	//正则表达式风格设置路径访问限制
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.
		authorizeRequests()
		//限定'/user/welcome'请求赋予角色ROLE_USER或者ROLE_ADMIN
		.regexMatchers("/user/welcome","/user/details").hasAnyRole("USER","ADMIN")
		//限定'/admin/'下所有请求权限赋予角色ROLE_ADMIN
		.regexMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
		//其他路径允许签名后访问
		.anyRequest().permitAll()
		//对于没有配置权限的其他请求允许匿名访问
		.and().anonymous()
		//使用Spring Security默认的登录页面
		.and().formLogin()
		//启动HTTP基础验证
		.and().httpBasic();
	}*/
	
/*	//Spring 表达式设置权限
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.
		authorizeRequests()
		//使用Spring表达式限定只有角色ROLE_USER或者ROLE_ADMIN
		.antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN')")
		//设置访问权限给角色ROLE_ADMIN,要求是完整登录(非记住我登录 )
		.antMatchers("/admin/welcome1")
		.access("hasAuthority('ROLE_ADMIN') && ifFullyAuthenticated()")
		//限定"/admin/welcome2"访问权限给角色ROLE_ADMIN,允许不完整登录
		.antMatchers("/admin/welcome2")
		.access("hasAuthority('ROLE_ADMIN')")
		//使用记住我功能
		.and().rememberMe()
		//使用Spring Security默认的登录页面
		.and().formLogin()
		//启动HTTP基础验证
		.and().httpBasic();
	}*/
	
	//强制使用HTTPS请求 
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		//使用安全渠道，限定为https请求
		.requiresChannel().antMatchers("/admin/**").requiresSecure()
		//不适用HTTPS请求
		.and().requiresChannel().antMatchers("/user/**").requiresInsecure()
		//限定允许的访问角色
		.and().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasAnyAuthority("USER","ADMIN");
	}
}

