package com.lcclovehww.springboot.chapter12.main;

import javax.crypto.SecretKey;
import javax.sql.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@SpringBootApplication(scanBasePackages="com.lcclovehww.springboot.chapter12")
@MapperScan(basePackages="com.lcclovehww.springboot.chapter12", annotationClass = Mapper.class)
@EnableCaching
public class Chapter12Application extends WebSecurityConfigurerAdapter{
	
/*	@Value("${system.user.password.secret}")
	private String secret = null;*/
	
	@Autowired
	UserDetailsService userDetailsService= null;

/*	@Autowired
	private DataSource dataSource = null;*/
	
	String pwdQuery = "select user_name, pwd, available " + "from t_user where user_name=?";
	
	String roleQuery = "select u.user_name, r.role_name"
						+" from t_user u, t_user_role ur, t_role r"
						+" where u.id = ur.user_id and r.id=ur.role_id"
						+" and u.user_name = ?";
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter12Application.class, args);
	}

/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String password1 = passwordEncoder.encode("abc");
		System.out.println("abc="+password1);
		String password2 = passwordEncoder.encode("123456");
		System.out.println("123456="+password2);
		auth.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder)
			.withUser("admin")
			.password(password1)
			.roles("USER","ADMIN")
			.and()
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
		
		auth.jdbcAuthentication()
		.passwordEncoder(passwordEncoder)
		.dataSource(dataSource)
		.usersByUsernameQuery(pwdQuery)
		.authoritiesByUsernameQuery(roleQuery);
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')")
		.and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
		.and().httpBasic()
		.and().authorizeRequests().antMatchers("/**").permitAll()
		.and().formLogin().loginPage("/login/page")
		.defaultSuccessUrl("/admin/welcome1");
	}
}

