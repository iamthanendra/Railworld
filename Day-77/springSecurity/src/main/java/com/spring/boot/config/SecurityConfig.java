package com.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails normalUser = User.withUsername("normal")
				.password(passwordEncoder().encode("12345")).roles("NORMAL")
				.build();
		
		UserDetails adminUser = User.withUsername("admin")
				.password(passwordEncoder().encode("12345")).roles("ADMIN")
				.build();
		
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(normalUser, adminUser);
		
		return inMemoryUserDetailsManager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		/*
		httpSecurity.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/home/public")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
		*/
		
		/*
		httpSecurity.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/home/normal")
		.hasRole("NORMAL")
		.requestMatchers("/home/admin/*")
		.hasRole("ADMIN")
		.requestMatchers("/home/public")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin(); */
		
		/*
		httpSecurity.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/home/normal")
		.hasRole("NORMAL")
		.requestMatchers("/home/admin")
		.hasRole("ADMIN")
		.requestMatchers("/home/public")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
		*/
		
		httpSecurity.csrf().disable()  //method level
		.authorizeHttpRequests()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
		
		
		return httpSecurity.build();
	}

}
