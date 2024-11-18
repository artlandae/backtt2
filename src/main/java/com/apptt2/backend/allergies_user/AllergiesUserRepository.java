package com.apptt2.backend.allergies_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergiesUserRepository extends JpaRepository<AllergiesUser , Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
