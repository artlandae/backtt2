package com.apptt2.backend.cat_role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_role", schema = "protocolo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatRole {
    @Id
    private int id;
    private String role;
}
