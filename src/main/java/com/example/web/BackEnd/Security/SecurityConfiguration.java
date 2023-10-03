package com.example.web.BackEnd.Security;

import com.example.web.BackEnd.Security.BCryptUserPassword.UserModelDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserModelDetailsService userModelDetailsService;

    @Autowired
    public SecurityConfiguration(UserModelDetailsService userModelDetailsService) {
        this.userModelDetailsService = userModelDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userModelDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/usersRegistration/**").permitAll()
                .antMatchers("/bookManager/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("http://localhost:63342/web/com/example/web/FrontEnd/Users_Registration/LoginPage/UserLoginPage.html")
                .permitAll()
                .and()
                .httpBasic()
                .and()

                //TODO create logout page
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/goodbye")
                .permitAll()

                .and()
                .cors()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}