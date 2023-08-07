package com.mstech.springsecurityjpa.security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.mstech.springsecurityjpa.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private static final String myAdmin = "admin";
  private static final String roleA = "ADMIN";

  // This Following block of code is enough to make Spring Security 
  // to use the Custom User Details Service for Authentication Purpose.
  // This is just like instatiation all the logic is writter in Custom Class.
  @Bean
  public UserDetailsService MyUserDetailsService(){
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
          .hasRole(roleA)
          .requestMatchers(mvcMatcherBuilder.pattern("/user"))
          .hasAnyRole(roleA, "USER")
          .requestMatchers(mvcMatcherBuilder.pattern("/"))
          .permitAll()
      )
      .formLogin(Customizer.withDefaults());
    return http.build();
  }
}
