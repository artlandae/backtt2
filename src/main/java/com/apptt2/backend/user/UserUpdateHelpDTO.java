package com.apptt2.backend.user;

import java.util.Date;

import lombok.Data;

@Data
public class UserUpdateHelpDTO {
    private String status;
    private String latitud;
    private String longitud;
    private Date date;
}
