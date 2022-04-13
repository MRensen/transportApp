package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.AuthenticationRequestDto;
import com.MRensen.transportApp.DTO.AuthenticationResponseDto;
import com.MRensen.transportApp.DTO.UserOutputDto;
import com.MRensen.transportApp.exception.BadRequestException;
import com.MRensen.transportApp.service.UserAuthenticateService;
import com.MRensen.transportApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
        UserOutputDto user = null;
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(authName.equals(username)) {
            user = UserOutputDto.fromUser(userService.getByUsername(username));
        } else {
            throw new BadRequestException("Username does not match search parameter");
        }
        return ResponseEntity.ok().body(user);
    }

    @PatchMapping(value="user/{username}/photo")
    public ResponseEntity<Object> setPhoto(@PathVariable String username, @RequestParam MultipartFile image){
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            if(authName.equals(username)) {
                userService.updatePhoto(username, image);
            } else {
                throw new BadRequestException("Username does not match search parameter");
            }

        } catch(IOException e){throw new BadRequestException("IOException was thrown");
        }
        return ResponseEntity.noContent().build();
    }

    //This doesn't show a photo in Postman, because it is missing the "data:image/jpeg;base64," prefix.
    @GetMapping(value = "user/{username}/photo")
    public ResponseEntity<String> getPhoto(@PathVariable String username){
        String image = userService.getPhoto(username);
        MediaType contentType = MediaType.IMAGE_PNG;
        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + username).body(image);
    }


}
