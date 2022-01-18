package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.AuthenticationRequestDto;
import com.MRensen.transportApp.DTO.AuthenticationResponseDto;
import com.MRensen.transportApp.service.UserAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthenticationController {

    UserAuthenticateService userAuthenticateService;

    @Autowired
    public UserAuthenticationController(UserAuthenticateService userAuthenticateService) {
        this.userAuthenticateService = userAuthenticateService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) {

        AuthenticationResponseDto authenticationResponseDto = userAuthenticateService.authenticateUser(authenticationRequestDto);

        return ResponseEntity.ok(authenticationResponseDto);
    }

    @GetMapping(value = "/authenticate/compare")
    public  ResponseEntity<Object> comparePasswords(@RequestBody AuthenticationRequestDto authenticationRequestDto){
       boolean result = userAuthenticateService.comparePassword(authenticationRequestDto.getPassword(), authenticationRequestDto.getOldPassword());
       return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/authenticate/encrypt")
    public  ResponseEntity<Object> encryptPassword(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        String result = userAuthenticateService.encryptPassword(authenticationRequestDto.getPassword());
        return ResponseEntity.ok().body(result);
    }
}
