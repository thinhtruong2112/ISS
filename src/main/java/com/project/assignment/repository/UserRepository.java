package com.project.assignment.repository;

import com.project.assignment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password")
    UserEntity findByUsernameAndPassword(String username, String password);

    Optional<UserEntity> findByUsername(String username);
}
