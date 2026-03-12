package com.lunarvoid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.lunarvoid.dto.RetanguloDTOA;
import com.lunarvoid.dto.RetanguloDTOR;
import com.lunarvoid.entities.Retangulo;
import com.lunarvoid.repositories.RetanguloRepository;

@Service
public class RetanguloService implements FormaService<Retangulo,Long,RetanguloDTOR,RetanguloDTOA> {
    @Autowired
    RetanguloRepository repository;

    @Override
    public JpaRepository<Retangulo,Long> getRepository(){
        return repository;
    }
}
