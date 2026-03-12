package com.lunarvoid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.lunarvoid.dto.QuadradoDTOA;
import com.lunarvoid.dto.QuadradoDTOR;
import com.lunarvoid.entities.Quadrado;
import com.lunarvoid.repositories.QuadradoRepository;

@Service
public class QuadradoService implements FormaService<Quadrado,Long,QuadradoDTOR,QuadradoDTOA> {
    @Autowired
    QuadradoRepository repository;

    @Override
    public JpaRepository<Quadrado,Long> getRepository(){
        return repository;
    }
}
