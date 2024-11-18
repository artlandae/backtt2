package com.apptt2.backend.user;

import com.apptt2.backend.cat_role.CatRole;
import lombok.Data;

import java.util.Date;

@Data
public class UserUpdateDTO {
    private CatRole role;  // Se puede actualizar el rol
    private String emailAddress;
    private String password;
    private String name;
    private String secondName;
    private String lastName;
    private String motherLastName;
    private String bloodType;
    private String birthDate;
    private String sex;
    private String allergies;
    private String criticalIllnes;
    private String status;
    private String cellPhone;
    private String auxiliaryCellPhone;
    private String latitud;
    private String lenght;
    private Date date;  // Se puede actualizar la fecha
}