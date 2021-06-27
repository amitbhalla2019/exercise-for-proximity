package com.abhalla.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    // Create 2 users with role
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.httpBasic()
        .and()
        .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/subjects/**").hasRole("USER")
            .antMatchers(HttpMethod.POST, "/subjects").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/subjects/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/subjects/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/subjects/**").hasRole("ADMIN")
        .and()
           .csrf()
           .disable()
           .formLogin()
           .disable();
    }

}