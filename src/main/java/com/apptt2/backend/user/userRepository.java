package com.apptt2.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, Integer> {
    // Puedes agregar métodos personalizados aquí
    @Query("SELECT u FROM User u WHERE u.emailAddress = :email")
    User findByEmail(@Param("email") String email);
}

