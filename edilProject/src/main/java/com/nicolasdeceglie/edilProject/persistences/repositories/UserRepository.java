package com.nicolasdeceglie.edilProject.persistences.repositories;

import com.nicolasdeceglie.edilProject.persistences.entitites.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail (String email);
}
