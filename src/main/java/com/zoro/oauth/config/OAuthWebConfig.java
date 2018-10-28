package com.zoro.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class OAuthWebConfig extends WebSecurityConfigurerAdapter {

	// authurize URLs starting with something
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/oauth/token/revokeById/**").permitAll()
				.antMatchers("/admin/**").hasRole("admin").antMatchers("/tokens/**").permitAll().anyRequest()
				.authenticated().and().formLogin().permitAll().and().csrf().disable();

//		http.csrf().disable().authorizeRequests().antMatchers("/api/user/**").hasAnyRole("user").and()
//				.authorizeRequests().antMatchers("/api/admin/**").hasAnyRole("admin").and().formLogin();

//			http
//            .authorizeRequests()
//                .antMatchers("/login", "/signup", "/about").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
//                .antMatchers("/oauth/token/revokeById/**").permitAll()
//    			.antMatchers("/tokens/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .failureUrl("/login?error")
//                .loginPage("/login")
//                .permitAll()
//                .and()
//            .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/index")
//                .permitAll()
//                .and()
//            .httpBasic()
//                .disable();

	}

	// AuthenticationManager can build a inmemory auth or database auth ; // default user in Oauth // Approval page
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		    .withUser("admin").password("admin").roles("admin")
		.and()
		    .withUser("wang").password("wang").roles("user");
	}

	// ignore URLs starting with /resources/
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/favor.ico");
	}
}
