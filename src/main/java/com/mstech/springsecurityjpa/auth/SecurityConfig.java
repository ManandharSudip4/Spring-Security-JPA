// package com.mstech.springsecurityjpa.auth;

// import com.mstech.springsecurityjpa.service.MyUserDetailsService;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//   private final MyUserDetailsService userDetailsService;

//   private static final String ROLE_ADMIN = "ADMIN";

//   public SecurityConfig(MyUserDetailsService customUserDetailsService) {
//     this.userDetailsService = customUserDetailsService;
//   }

//   //   @Bean
//   //   public AuthenticationManager authenticationManager(
//   //     AuthenticationConfiguration authenticationConfiguration
//   //   ) throws Exception {
//   //     return authenticationConfiguration.getAuthenticationManager();
//   //   }

//   @SuppressWarnings("deprication")
//   @Bean
//   public AuthenticationManager authenticationManager(
//     HttpSecurity http,
//     NoOpPasswordEncoder noOpPasswordEncoder
//   ) throws Exception {
//     AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
//       AuthenticationManagerBuilder.class
//     );
//     authenticationManagerBuilder
//       .userDetailsService(userDetailsService)
//       .passwordEncoder(noOpPasswordEncoder);
//     return authenticationManagerBuilder.build();
//   }

//   @Bean
//   public SecurityFilterChain securityFilterChain(HttpSecurity http)
//     throws Exception {
//     http
//       .csrf(AbstractHttpConfigurer::disable)
//       .authorizeHttpRequests(authorize ->
//         authorize
//           .requestMatchers("/authenticate")
//           .permitAll()
//           .requestMatchers("/admin")
//           .hasRole(ROLE_ADMIN)
//           .requestMatchers("/user")
//           .hasAnyRole(ROLE_ADMIN, "USER")
//           .requestMatchers("/")
//           .permitAll()
//           .anyRequest()
//           .authenticated()
//       )
//       .formLogin(Customizer.withDefaults());

//     return http.build();
//   }

//   @SuppressWarnings("deprecation")
//   @Bean
//   public NoOpPasswordEncoder passwordEncoder() {
//     return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//   }
// }
