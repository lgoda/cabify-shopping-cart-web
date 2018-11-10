package com.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    
    Optional<Product> findByCode(String code);
    
}
