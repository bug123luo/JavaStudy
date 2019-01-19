package com.lcclovehww.springboot.chapter12.main;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages="com.lcclovehww.springboot.chapter12")
public class Chapter12Application extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource = null;
	
	String pwdQuery = "select user_name, pwd, available" + "from t_user where user_name=?";
	
	String roleQuery = "select u.user_name, r.role_name"
						+" from t_user u, t_user_role ur, t_role r"
						+"where u.id = ur.user_id and r.id=ur.role_id"
						+" and u.user_name = ?";
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter12Application.class, args);
	}

/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder)
			.withUser("admin")
			.password(passwordEncoder.encode("abc"))
			.roles("USER","ADMIN")
			.and()
			.withUser("myuser")
			.password(passwordEncoder.encode("123456"))
			.roles("USER");
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		auth.jdbcAuthentication()
			.passwordEncoder(passwordEncoder)
			.dataSource(dataSource)
			.usersByUsernameQuery(pwdQuery)
			.authoritiesByUsernameQuery(roleQuery);
		
	}
}

