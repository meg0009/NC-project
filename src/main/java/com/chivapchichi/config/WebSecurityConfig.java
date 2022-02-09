package com.chivapchichi.config;

import com.chivapchichi.service.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.jdbcAuthentication().dataSource(dataSource)
                        /*.usersByUsernameQuery(
                                "select user_name as username, password from users where user_name=?"
                        )
                        .authoritiesByUsernameQuery(
                                "select user_name as username, role from users where user_name=?"
                        )*/
                .usersByUsernameQuery(
                        "select user_name, password, 'true' from users where user_name=?"
                ).authoritiesByUsernameQuery(
                        "select user_name, role from users where user_name=?"
                ).and()
                .inMemoryAuthentication()
                .withUser(RestApiService.adminName).password(passwordEncoder().encode(RestApiService.adminPassword)).roles("ADMIN", "USER");
        /*auth.inMemoryAuthentication()
                *//*.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER")
                .and()*//*
                .withUser(RestApiService.adminName).password(passwordEncoder().encode(RestApiService.adminPassword)).roles("ADMIN", "USER");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable()
                /*.requestMatchers()
                .antMatchers("rest")
                .and()*/
                .authorizeRequests()
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers("/rest/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                //.loginProcessingUrl("/perform-login")
                .defaultSuccessUrl("/homepage", true)
                .and()
                .httpBasic()
                /*.and()
                .logout()
                .logoutUrl("/perform-logout")*/;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
