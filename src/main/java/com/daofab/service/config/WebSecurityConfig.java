package com.daofab.service.config;


import com.daofab.service.filter.RequestHeaderFilter;
import com.daofab.service.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

/**
 * @author Mahendra on 5/17/2023
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private  final RequestHeaderFilter requestHeaderFilter;

  @Autowired
  private UserDetailsService userDetailsService;


  public WebSecurityConfig(RequestHeaderFilter requestHeaderFilter) {
    this.requestHeaderFilter = requestHeaderFilter;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/configuration/ui", "/swagger-ui.html", "/v2/api-docs",
        "/webjars/**", "/swagger-resources/**", "/configuration/security",
        "/auth/login");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().formLogin().disable()
            .authorizeRequests()
            .antMatchers("/auth/**")
            .permitAll().and()
            .httpBasic()
            .authenticationEntryPoint(new RestAuthenticationEntryPoint())
        .and().authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .cors().and()
        .addFilterBefore(requestHeaderFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Autowired
  DataSource dataSource;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
