package com.MRensen.transportApp.service;

import com.MRensen.transportApp.DTO.AuthenticationRequestDto;
import com.MRensen.transportApp.DTO.AuthenticationResponseDto;
import com.MRensen.transportApp.config.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class UserAuthenticateServiceTest {

    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    CustomUserDetailsService userDetailsService;

    @Mock
    JwtUtil jwtUtil;

    @InjectMocks
    UserAuthenticateService userAuthenticateService = new UserAuthenticateService(authenticationManager, userDetailsService, jwtUtil);


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
