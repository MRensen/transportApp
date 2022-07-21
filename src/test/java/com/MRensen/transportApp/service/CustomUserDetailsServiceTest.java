package com.MRensen.transportApp.service;


import com.MRensen.transportApp.model.Authority;
import com.MRensen.transportApp.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {


    @Mock
    UserService userService;

    @InjectMocks
    CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService(userService);




    @Test
    void loadUserByUsernameReturnsUserDetails(){

        User user = new User();
        user.setUsername("testman");
        user.setPassword("password");
        Set<Authority> auth = new HashSet<>();
        auth.add(new Authority("testman", "test"));
        user.setAuthorities(auth);

        UserDetails expectedDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("test"));
                return Collections.unmodifiableSet(grantedAuthorities);
            }

            @Override
            public String getPassword() {
                return "password";
            }

            @Override
            public String getUsername() {
                return "testman";
            }

            @Override
            public boolean isEnabled() {
                return true;
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
        };
        Mockito
                .when(userService.getByUsername(anyString()))
                .thenReturn(user);

        UserDetails actualDetails = customUserDetailsService.loadUserByUsername("testman");

        assertEquals(expectedDetails.getUsername(), actualDetails.getUsername());
        assertEquals(expectedDetails.getPassword(), actualDetails.getPassword());
        assertEquals(expectedDetails.getAuthorities(), actualDetails.getAuthorities());

    }

    @Test
    void loadUserByUsernameThrowsUsernameNotFoundException(){
        Mockito
                .when(userService.getByUsername(anyString()))
                .thenReturn(null);

        assertThrows(UsernameNotFoundException.class, ()->{customUserDetailsService.loadUserByUsername("testman");});
    }
}


