package com.apptt2.backend.cat_criticall_illnes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_critical_illnes", schema = "protocolo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatCriticalIllnes {
    @Id
    private int id;
    private String criticalIllnes;
}
