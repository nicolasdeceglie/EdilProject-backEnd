package com.nicolasdeceglie.edilProject.persistences.repositories;

import com.nicolasdeceglie.edilProject.persistences.entitites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
