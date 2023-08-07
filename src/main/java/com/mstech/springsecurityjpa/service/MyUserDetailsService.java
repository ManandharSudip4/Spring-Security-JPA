package com.mstech.springsecurityjpa.service;

import java.util.Arrays;
// import com.mstech.springsecurityjpa.entities.Users;
// import com.mstech.springsecurityjpa.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

//   private final UsersRepository usersRepository;

//   public MyUserDetailsService(UsersRepository usersRepository) {
//     this.usersRepository = usersRepository;
//   }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    // Users loadedUsers = usersRepository
    //   .findByUserName(username)
    //   .orElseThrow(() -> new UsernameNotFoundException("Could not Found!"));

    // return new User(
    //   loadedUsers.getUserName(),
    //   loadedUsers.getPassword(),
    //   false,
    //   false,
    //   false,
    //   false,
    //   Collections.singleton(new SimpleGrantedAuthority(loadedUsers.getRoles()))
    // );
    return new MyUserDetails();
  }
}
