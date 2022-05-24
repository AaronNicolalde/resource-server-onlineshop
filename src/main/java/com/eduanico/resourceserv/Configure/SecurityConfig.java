package com.eduanico.resourceserv.Configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {// @formatter:off
        http.authorizeRequests().antMatchers("/actuator/health")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/swagger-ui/index.html")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/swagger-ui.html")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/swagger-ui/**")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/api-docs/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
    }//@formatter:on

}