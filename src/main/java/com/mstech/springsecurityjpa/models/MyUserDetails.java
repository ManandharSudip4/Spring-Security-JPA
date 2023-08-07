package com.mstech.springsecurityjpa.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

  private String userName;
  private String password;
  private boolean active;
  private List<GrantedAuthority> authorities;

  public MyUserDetails(Users user) {
    this.userName = user.getUserName();
    this.password = user.getPassword();
    this.active = user.isActive();
    this.authorities =
      Arrays
        .stream(user.getRoles().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  // user.getRoles().split(","): This takes the comma-separated roles string from the Users object and 
  //   splits it into an array of individual role strings.
  // .stream(): This converts the array into a stream, allowing you to perform operations on 
  //   each element.
  // .map(SimpleGrantedAuthority::new): This maps each role string to a SimpleGrantedAuthority object. 
  //   A SimpleGrantedAuthority is an implementation of the GrantedAuthority interface provided by Spring Security. It represents a single authority (role) that a user possesses.
  // .collect(Collectors.toList()): This collects the SimpleGrantedAuthority objects from the stream and 
  //   gathers them into a list.

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return active;
  }
}
