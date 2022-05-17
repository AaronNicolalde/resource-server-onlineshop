//package com.eduanico.resourceserv.Configure;
//
//import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.requestMatcher(EndpointRequest.to("health"))
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll();
//    }
////
//////    @Override
//////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//////        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//////        auth
//////                .inMemoryAuthentication()
//////                .withUser("user")
//////                .password(encoder.encode("password"))
//////                .roles("USER")
//////                .and()
//////                .withUser("admin")
//////                .password(encoder.encode("admin"))
//////                .roles("USER", "ADMIN");
//////    }
////
////
//}