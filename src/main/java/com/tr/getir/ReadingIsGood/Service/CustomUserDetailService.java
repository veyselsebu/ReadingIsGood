package com.tr.getir.ReadingIsGood.Service;

import com.tr.getir.ReadingIsGood.Model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;

@Service
public class CustomUserDetailService implements Serializable, UserDetailsService {
    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      com.tr.getir.ReadingIsGood.Model.User user = userService.getUserById(username);
        return new CustomUserDetails(user);
    }
}
