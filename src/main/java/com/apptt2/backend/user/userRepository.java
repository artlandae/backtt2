package com.apptt2.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u.role.id AS password FROM User u WHERE u.emailAddress = :emailAddress AND u.password = :password")
    Optional<Integer> findRoleByEmailAndPassword(@Param("emailAddress") String emailAddress, @Param("password") String password);


    @Query("SELECT u.id AS id, u.name AS name, u.cellPhone AS cellPhone, u.auxiliaryCellPhone AS auxiliaryCellPhone, u.date AS date FROM User u WHERE u.status = 'true' ")
    List<UserStatus> findByallstatus();

@Query("SELECT u.id AS id, u.name AS name, u.cellPhone AS cellPhone, u.auxiliaryCellPhone AS auxiliaryCellPhone, u.date AS date " +
       "FROM User u " +
       "WHERE u.status = 'true' " + 
       "AND u.date >= :startDate " +
       "AND u.date < :endDate")
List<UserStatus> findBystatus(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);




    @Query("SELECT u.id AS id, u.password AS password " +
    "FROM User u " +
    "WHERE u.emailAddress = :emailAddress AND u.password = :password")
    Optional<UserIdPasswordProjection> findIdAndPasswordByEmailAndPasswordAndRole(
         @Param("emailAddress") String emailAddress,
         @Param("password") String password
    );

    Optional<User> findByEmailAddress(String emailAddress); // New method for finding user by email

    // Updated method to find user by token (which matches the password)
    @Query("SELECT u FROM User u WHERE u.password = :token")
    Optional<User> findByToken(@Param("token") String token);
}
