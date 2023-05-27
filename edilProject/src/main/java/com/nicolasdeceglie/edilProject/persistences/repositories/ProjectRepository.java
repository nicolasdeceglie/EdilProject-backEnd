package com.nicolasdeceglie.edilProject.persistences.repositories;

import com.nicolasdeceglie.edilProject.persistences.entitites.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> deleteByName(String name);
}
