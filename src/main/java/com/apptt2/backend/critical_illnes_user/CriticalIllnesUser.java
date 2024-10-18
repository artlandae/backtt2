package com.apptt2.backend.critical_illnes_user;

import com.apptt2.backend.cat_criticall_illnes.CatCriticalIllnes;
import com.apptt2.backend.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "critical_illnes_user", schema = "protocolo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriticalIllnesUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_cat_critical_illnes")
    private CatCriticalIllnes criticalIllnes;
}
