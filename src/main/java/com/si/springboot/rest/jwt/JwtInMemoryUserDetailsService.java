package com.si.springboot.rest.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.si.springboot.rest.model.User;
import com.si.springboot.rest.service.UserService;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {
	
  @Autowired
  UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userService.findByUsername(username);
	
    if (user == null) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }
   
    return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword());
  }

}


