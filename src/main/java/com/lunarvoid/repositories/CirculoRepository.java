package com.lunarvoid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lunarvoid.entities.Circulo;

@Repository
public interface CirculoRepository extends JpaRepository<Circulo,Long> {}

