package com.apptt2.backend.user;


import lombok.Data;

@Data
public class UserLoginDTO {

    private String emailAddress;
    private String password;
}
