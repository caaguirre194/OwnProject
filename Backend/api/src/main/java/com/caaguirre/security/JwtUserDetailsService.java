package com.caaguirre.security;

import com.caaguirre.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private BCryptPasswordEncoder x;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.caaguirre.model.User user = userService.findByUsername(username);

        if (user.getUsername().equals(username)) {
            return new User(user.getUsername(), bcrypt.encode(user.getPassword()),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
