package com.example.internship.repository;

import com.example.internship.entity.RoleName;
import com.example.internship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    List<User> findByRole(RoleName roleName);

    @Query("SELECT COUNT(u) FROM User u WHERE u.email = :email AND (:userId IS NULL OR u.userId <> :userId)")
    Long isExistEmail(@Param("email") String email, @Param("userId") Integer id);

}
