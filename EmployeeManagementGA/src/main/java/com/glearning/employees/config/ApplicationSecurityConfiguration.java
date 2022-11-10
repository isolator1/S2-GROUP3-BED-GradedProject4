package com.glearning.employees.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.glearning.employees.service.DomainUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final DomainUserDetailsService userDetailsService;
	
	//authentication
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		// configure users
		/*
		 * authenticationManagerBuilder .inMemoryAuthentication() .withUser("kiran")
		 * .password(passwordEncoder().encode("welcome")) .roles("USER") .and()
		 * .withUser("vinay") .password(passwordEncoder().encode("welcome"))
		 * .roles("USER", "ADMIN");
		 */
         authenticationManagerBuilder
                 .userDetailsService(this.userDetailsService)
                 .passwordEncoder(passwordEncoder());

	}
	
	//authorization
	 @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        // configure authorization rules here
	        httpSecurity.cors().disable();
	        httpSecurity.csrf().disable();
	        httpSecurity.headers().frameOptions().disable();
	        httpSecurity
	                .authorizeRequests()
	                	.antMatchers(GET,"/api/employees/**")
	                		//.hasAnyRole("USER", "ADMIN")
	                	.permitAll()
	                .antMatchers("/h2-console/**", "/login**", "/contact-us**")
	                 	.permitAll()
	                .and()
	                .authorizeRequests()
	                .antMatchers(HttpMethod.POST,"/api/employees/**")
	                	.hasAnyRole("ADMIN","MANAGER")
	                .antMatchers(HttpMethod.PUT,"/api/employees/**")
	                	.hasAnyRole("ADMIN","MANAGER")
	                .antMatchers(HttpMethod.DELETE,"/api/employees/**")
	                	.hasRole("MANAGER")
	                .anyRequest()
	                	.authenticated()
	                .and()
	                	.formLogin()
	                .and()
	                	.httpBasic()
	               .and()
	                /*
	                   Set the sessioncreation policy to avoid using cookies for authentication
	                   https://stackoverflow.com/questions/50842258/spring-security-caching-my-authentication/50847571
	                 */
	                .sessionManagement().sessionCreationPolicy(STATELESS);
	    }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }


}
