//package com.example.springangularreddit.config;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//// Doesn't seem to import anything. Maybe I got to change something with Maven or maybe this class is Obsolete
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .authorizeRequests() // this method is obsolete?
//                .antMatchers("/api/auth/**") // this method doesn't even seem to exist
//                .permitAll()
//                .anyRequest()
//                .authenticated();
//    }
//
//}
