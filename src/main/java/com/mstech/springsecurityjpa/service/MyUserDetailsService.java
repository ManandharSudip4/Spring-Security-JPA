package com.mstech.springsecurityjpa.service;

import com.mstech.springsecurityjpa.models.MyUserDetails;
import com.mstech.springsecurityjpa.models.Users;
import com.mstech.springsecurityjpa.repository.UsersRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired // this autowire is required here.
  UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String userName)
    throws UsernameNotFoundException {
    Optional<Users> user = usersRepository.findByUserName(userName);
    // retrive the user credentials from the database with the help of username provided.

    user.orElseThrow(() ->
      new UsernameNotFoundException("Not found: " + userName)
    );

    return user.map(MyUserDetails::new).get();
  }
}
