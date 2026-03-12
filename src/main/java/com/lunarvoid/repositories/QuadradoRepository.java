package com.lunarvoid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lunarvoid.entities.Quadrado;

@Repository
public interface QuadradoRepository extends JpaRepository<Quadrado,Long> {}

