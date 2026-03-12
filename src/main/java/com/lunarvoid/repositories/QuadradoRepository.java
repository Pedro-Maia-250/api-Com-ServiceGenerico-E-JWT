package com.lunarvoid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunarvoid.entities.Quadrado;

public interface QuadradoRepository extends JpaRepository<Quadrado,Long> {
    
}
