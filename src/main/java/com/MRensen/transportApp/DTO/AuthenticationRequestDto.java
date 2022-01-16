package com.MRensen.transportApp.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthenticationRequestDto {


    private String username;

    @Size(min=8)
    private String password;

    private String oldPassword;

    public AuthenticationRequestDto() {
    }
    public AuthenticationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequestDto(String username, String password, String oldPassword) {
        this.username = username;
        this.password = password;
        this.oldPassword = oldPassword;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
