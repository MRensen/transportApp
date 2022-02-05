package com.MRensen.transportApp.service;

import com.MRensen.transportApp.DTO.AuthenticationRequestDto;
import com.MRensen.transportApp.DTO.AuthenticationResponseDto;
import com.MRensen.transportApp.TransportAppApplication;
import com.MRensen.transportApp.config.security.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_CLASS)
public class UserAuthenticateServiceTest {
    @Autowired
    UserAuthenticateService userAuthenticateService;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    CustomUserDetailsService userDetailsService;

    @MockBean
    JwtUtil jwtUtil;

    @Test
    void authenticateUserTest(){
        AuthenticationResponseDto expected = new AuthenticationResponseDto("test");

        Mockito
                .when(authenticationManager.authenticate(ArgumentMatchers.any()))
                .thenReturn(null);
        Mockito
                .when(userDetailsService.loadUserByUsername(anyString()))
                .thenReturn(null);
        Mockito
                .when(jwtUtil.generateToken(any()))
                .thenReturn("test");

        AuthenticationResponseDto actual = userAuthenticateService.authenticateUser(new AuthenticationRequestDto("test", "test"));

        assertEquals(expected.getJwt(), actual.getJwt());
    }
}
