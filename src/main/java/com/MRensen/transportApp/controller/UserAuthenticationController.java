package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.AuthenticationRequestDto;
import com.MRensen.transportApp.DTO.AuthenticationResponseDto;
import com.MRensen.transportApp.DTO.UserOutputDto;
import com.MRensen.transportApp.service.UserAuthenticateService;
import com.MRensen.transportApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAuthenticationController {

    UserAuthenticateService userAuthenticateService;
    UserService userService;

    public UserAuthenticateService getUserAuthenticateService() {
        return userAuthenticateService;
    }

    @Autowired
    public UserAuthenticationController(UserAuthenticateService userAuthenticateService, UserService userService) {
        this.userAuthenticateService = userAuthenticateService;
        this.userService = userService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) {

        AuthenticationResponseDto authenticationResponseDto = userAuthenticateService.authenticateUser(authenticationRequestDto);

        return ResponseEntity.ok(authenticationResponseDto);
    }

    @GetMapping(value="/user/{username}")
    public ResponseEntity<Object> getUser(@PathVariable String username){
        UserOutputDto user = UserOutputDto.fromUser(userService.getByUsername(username));
        return ResponseEntity.ok().body(user);
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
