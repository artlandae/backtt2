package com.apptt2.backend.critical_illnes_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriticalIllnesUserRepository extends JpaRepository<CriticalIllnesUser , Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}