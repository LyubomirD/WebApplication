package com.example.web.BackEnd.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * I am still learning SpringBootSecurity as you can see!
 *
 * I do not understand many of the methods, functions, annotations and functionality of
 * SpringBootSecurity so if someone is knowledgeable in this field,
 * it would be greatly appreciated help, to explain some concepts and
 * rewrite the faulty code!
 *
 * Thanks in advance!
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                 .and()
//                .antMatchers("/public/**").permitAll() // Allow access to public resources
//                .anyRequest().authenticated() // Require authentication for all other requests
//                .and()
//                .formLogin() // Enable form-based authentication
//                .and()
//                .logout() // Enable logout
//                .and()
                .csrf().disable(); // Disable CSRF protection for now
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
