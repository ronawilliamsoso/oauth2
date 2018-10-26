package com.zoro.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


 
@Configuration
public class OAuthWebConfig extends WebSecurityConfigurerAdapter {
	 


	    @Override
	    protected void configure(final HttpSecurity http) throws Exception {
	        // @formatter:off
			http.authorizeRequests().antMatchers("/login").permitAll()
			.antMatchers("/oauth/token/revokeById/**").permitAll()
			.antMatchers("/tokens/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().permitAll()
			.and().csrf().disable();
			// @formatter:on
			
			
			
			
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
	    
	    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  //AuthenticationManager can build a inmemory auth or database auth
        auth
            .inMemoryAuthentication()
            .withUser("admin").password("admin").roles("USER");    // default user in Oauth Approval page
    }
    
    
    
    
    

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/favor.ico");
    }
}
