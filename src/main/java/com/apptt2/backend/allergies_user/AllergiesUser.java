package com.apptt2.backend.allergies_user;

import com.apptt2.backend.cat_allergies.CatAllergies;
import com.apptt2.backend.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "allergies_user", schema = "protocolo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllergiesUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_user_allergies")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_allergies_user")
    private CatAllergies allergies;
}
