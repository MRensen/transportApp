package com.MRensen.transportApp.service;


import com.MRensen.transportApp.model.Authority;
import com.MRensen.transportApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService){
        this.userService = userService;
    }

//    @Autowired
//    private AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        String password = user.getPassword();
//        Long id;
//        switch (user.getRole()) {
//            case "driver" -> id = user.getDriver().getId();
//            case "customer" -> id = user.getCustomer().getId();
//            case "planner" -> id = user.getPlanner().getId();
//            default -> id = null;
//        }

        Set<Authority> authorities = user.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority: authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }

}