package com.apptt2.backend.user;

import lombok.Data;
import java.util.Date;

@Data
public class UserCreateDTO {
    private int id;
    private int roleId;  // ID del rol para asignarlo en la relación
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
    private String longitud;
    private Date date;
}
