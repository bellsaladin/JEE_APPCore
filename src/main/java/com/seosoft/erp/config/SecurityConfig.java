package com.seosoft.erp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.seosoft.erp.controller.security.CustomAuthenticationProvider;
import com.seosoft.erp.controller.security.CustomAuthenticationSuccessHandler;
import com.seosoft.erp.repository.parametrage.v2_UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private v2_UserRepository userRepository;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider());
		// .passwordEncoder(passwordEncoder());
		// .userDetailsService(customUserDetailsService());

	}

	@Bean
	public AuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider(userRepository);
	}

	// @Bean
	// public UserDetailsService customUserDetailsService() {
	// return new CustomUserDetailsService(userRepository);
	// }
	//
	// @Bean
	// public PasswordEncoder passwordEncoder() {
	// return new BCryptPasswordEncoder(10);
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/javax.faces.resource/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin().loginPage("/login").successHandler(new CustomAuthenticationSuccessHandler())
		/* .failureHandler(new CustomAuthenticationFailureHandler()) */.permitAll();
		http.logout().permitAll();
		http.headers()
		.frameOptions().sameOrigin()
		.httpStrictTransportSecurity().disable();
	}
}
