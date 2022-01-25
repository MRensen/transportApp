package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.AuthenticationRequestDto;
import com.MRensen.transportApp.DTO.AuthenticationResponseDto;
import com.MRensen.transportApp.DTO.UserOutputDto;
import com.MRensen.transportApp.exception.BadRequestException;
import com.MRensen.transportApp.service.UserAuthenticateService;
import com.MRensen.transportApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public ResponseEntity<Object> getUserDetails(@PathVariable String username){
        UserOutputDto user = UserOutputDto.fromUser(userService.getByUsername(username));
        return ResponseEntity.ok().body(user);
    }

    @PatchMapping(value="user/{username}/password")
    public ResponseEntity<Object> setPassword(@PathVariable String username, @RequestBody String password){
        userService.updatePassword(username, password);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value="user/{username}/photo")
    public ResponseEntity<Object> setPhoto(@PathVariable String username, @RequestBody MultipartFile image){
        try {
            userService.updatePhoto(username, image);
        } catch(IOException e){throw new BadRequestException("IOException was thrown");
        }
        return ResponseEntity.noContent().build();
    }


}
