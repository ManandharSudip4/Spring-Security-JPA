package com.mstech.springsecurityjpa.security;

import com.mstech.springsecurityjpa.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private static final String ROLE_ADMIN = "ADMIN";

  // This Following block of code is enough to make Spring Security
  // to use the Custom User Details Service for Authentication Purpose.
  // This is just like instatiation all the logic is writter in Custom Class.
  @Bean
  public UserDetailsService MyUserDetailsService() {
    return new MyUserDetailsService();
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  // Authorization
  @Bean
  public SecurityFilterChain securityFilterChain(
    HttpSecurity http,
    HandlerMappingIntrospector introspector
  ) throws Exception {
    MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(
      introspector
    );
    http
      .authorizeHttpRequests(authorize ->
        authorize
          .requestMatchers(mvcMatcherBuilder.pattern("/admin"))
          .hasRole(ROLE_ADMIN)
          .requestMatchers(mvcMatcherBuilder.pattern("/user"))
          .hasAnyRole(ROLE_ADMIN, "USER")
          .requestMatchers(mvcMatcherBuilder.pattern("/"))
          .permitAll()
      )
      .formLogin(Customizer.withDefaults());
    return http.build();
  }
}
