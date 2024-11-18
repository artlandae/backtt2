package com.apptt2.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apptt2.backend.cat_role.CatRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User, Integer> {
    // Puedes agregar métodos personalizados aquí
    @Query("SELECT u.name AS name, u.cellPhone AS cellPhone, u.auxiliaryCellPhone AS auxiliaryCellPhone FROM User u WHERE u.status = 'true'")
    List<UserStatus> findBystatus();

    @Query("SELECT u.id AS id, u.password AS password " +
    "FROM User u " +
    "WHERE u.emailAddress = :emailAddress " +
    "AND u.password = :password " +
    "AND u.role = :role")
Optional<UserIdPasswordProjection> findIdAndPasswordByEmailAndPasswordAndRole(
     @Param("emailAddress") String emailAddress,
     @Param("password") String password,
     @Param("role") CatRole role
);
}

