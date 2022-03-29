package com.chivapchichi.config;

import com.chivapchichi.service.security.JwtConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final JwtConfigurer jwtConfigurer;

    /*@Autowired
    DataSource dataSource;*/

    @Autowired
    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, JwtConfigurer jwtConfigurer) {
        this.userDetailsService = userDetailsService;
        this.jwtConfigurer = jwtConfigurer;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        //super.configure(auth);
       /* auth.jdbcAuthentication().dataSource(dataSource)
                        *//*.usersByUsernameQuery(
                                "select user_name as username, password from users where user_name=?"
                        )
                        .authoritiesByUsernameQuery(
                                "select user_name as username, role from users where user_name=?"
                        )*//*
                .usersByUsernameQuery(
                        "select user_name, password, 'true' from users where user_name=?"
                ).authoritiesByUsernameQuery(
                        "select user_name, role from users where user_name=?"
                ).and()
                .inMemoryAuthentication()
                .withUser(RestApiService.adminName).password(passwordEncoder().encode(RestApiService.adminPassword)).roles("ADMIN", "USER");*/
        /*auth.inMemoryAuthentication()
                *//*.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER")
                .and()*//*
                .withUser(RestApiService.adminName).password(passwordEncoder().encode(RestApiService.adminPassword)).roles("ADMIN", "USER");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                /*.requestMatchers()
                .antMatchers("rest")
                .and()*/
                .authorizeRequests()
                /*.antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers("/rest/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated()*/
                .antMatchers(HttpMethod.POST, "/tournament/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/tournament/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers("/admin-panel/**").hasRole("ADMIN")
                .antMatchers("/admin/api/**").hasRole("ADMIN")
                .antMatchers("/registration").permitAll()
                //.antMatchers(HttpMethod.GET).permitAll()
                .antMatchers("/login-api/auth/login").permitAll()
                .antMatchers("/login-api/auth/logout").hasAnyRole("ADMIN", "USER")
                .antMatchers("/login").permitAll()
                .antMatchers("/makelogout").hasAnyRole("ADMIN", "USER")
                .antMatchers("/registration/api").permitAll()
                .antMatchers("/main.js").permitAll()
                .antMatchers("/select.js").permitAll()
                .antMatchers("/style.css").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated()
                .and()
                //.formLogin()
                //.loginPage("/login")
                //.loginProcessingUrl("/perform-login")
                //.defaultSuccessUrl("/tournament/registration", true)
                //.and()
                .apply(jwtConfigurer);
                /*.and()
                .logout()
                .logoutUrl("/perform-logout")*/;
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
