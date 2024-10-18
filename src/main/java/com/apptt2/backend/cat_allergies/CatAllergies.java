package com.apptt2.backend.cat_allergies;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_allergies", schema = "protocolo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatAllergies {
    @Id
    private int id;
    private String allergie;
}
