package com.apptt2.backend.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

import com.apptt2.backend.cat_role.CatRole;

@Entity
@Table(name = "user", schema = "protocolo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private CatRole role;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
