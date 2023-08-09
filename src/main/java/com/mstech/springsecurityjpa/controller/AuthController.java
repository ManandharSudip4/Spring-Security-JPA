package com.mstech.springsecurityjpa.controller;

import com.mstech.springsecurityjpa.auth.JwtUtil;
import com.mstech.springsecurityjpa.models.AuthenticationRequest;
import com.mstech.springsecurityjpa.models.AuthenticationResponse;
import com.mstech.springsecurityjpa.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

  @Autowired
  private MyUserDetailsService userDetailsService;

  private final AuthenticationManager authenticationManager;

  private JwtUtil jwtUtil;

  public AuthController(
    AuthenticationManager authenticationManager,
    JwtUtil jwtUtil
  ) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  @ResponseBody
  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> authenticate(
    @RequestBody AuthenticationRequest authenticationRequest
  ) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authenticationRequest.getUsername(),
        authenticationRequest.getPassword()
      )
    );

    String username = authentication.getName();
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    String jwt = jwtUtil.generateToken(userDetails);

    AuthenticationResponse authenticationResponse = new AuthenticationResponse(
      jwt
    );

    return ResponseEntity.ok(authenticationResponse);
  }
}
