package com.lunarvoid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.lunarvoid.dto.CirculoDTOA;
import com.lunarvoid.dto.CirculoDTOR;
import com.lunarvoid.entities.Circulo;
import com.lunarvoid.repositories.CirculoRepository;

@Service
public class CirculoService implements FormaService<Circulo,Long,CirculoDTOR,CirculoDTOA> {

    @Autowired
    CirculoRepository repository;

    @Override
    public JpaRepository<Circulo,Long> getRepository(){
        return repository;
    }
}
