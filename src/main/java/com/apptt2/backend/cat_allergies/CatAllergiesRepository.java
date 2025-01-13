package com.apptt2.backend.cat_allergies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatAllergiesRepository extends JpaRepository<CatAllergies, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}