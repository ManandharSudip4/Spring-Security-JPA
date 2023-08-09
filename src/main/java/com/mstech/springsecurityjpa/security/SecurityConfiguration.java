package com.mstech.springsecurityjpa.security;

import com.mstech.springsecurityjpa.auth.JwtRequestFilter;
import com.mstech.springsecurityjpa.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private static final String ROLE_ADMIN = "ADMIN";

  // @Bean
  // public AuthenticationManager authenticationManager(
  //   AuthenticationConfiguration authenticationConfiguration
  // ) throws Exception {
  //   return authenticationConfiguration.getAuthenticationManager();
  // }

  // This Following block of code is enough to make Spring Security
  // to use the Custom User Details Service for Authentication Purpose.
  // This is just like instatiation all the logic is writter in Custom Class.

  private final MyUserDetailsService userDetailsService;

  public SecurityConfiguration(MyUserDetailsService myUserDetailsService) {
    this.userDetailsService = myUserDetailsService;
  }

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @SuppressWarnings("deprecation")
  @Bean
  public AuthenticationManager authenticationManager(
    HttpSecurity http,
    NoOpPasswordEncoder noOpPasswordEncoder
  ) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
      AuthenticationManagerBuilder.class
    );
    authenticationManagerBuilder
      .userDetailsService(userDetailsService)
      .passwordEncoder(noOpPasswordEncoder);
    return authenticationManagerBuilder.build();
  }

  @SuppressWarnings("deprecation")
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  // Authorization
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(authorize ->
        authorize
          .requestMatchers("/authenticate")
          .permitAll()
          .requestMatchers("/admin")
          .hasRole(ROLE_ADMIN)
          .requestMatchers("/user")
          .hasAnyRole(ROLE_ADMIN, "USER")
          .requestMatchers("/")
          .permitAll()
          .anyRequest()
          .authenticated()
      )
      .sessionManagement(manager ->
        manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .addFilterBefore(
        jwtRequestFilter,
        UsernamePasswordAuthenticationFilter.class
      )
      .formLogin(Customizer.withDefaults());
    return http.build();
  }
}