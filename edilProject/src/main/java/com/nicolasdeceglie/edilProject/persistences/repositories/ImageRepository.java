package com.nicolasdeceglie.edilProject.persistences.repositories;

import com.nicolasdeceglie.edilProject.persistences.entitites.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
