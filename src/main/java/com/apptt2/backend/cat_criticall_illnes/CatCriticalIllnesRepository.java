package com.apptt2.backend.cat_criticall_illnes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatCriticalIllnesRepository extends JpaRepository<CatCriticalIllnes, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}