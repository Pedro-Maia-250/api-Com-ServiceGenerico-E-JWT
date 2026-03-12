package com.lunarvoid.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.lunarvoid.entities.Quadrado;
import com.lunarvoid.repositories.QuadradoRepository;
import com.lunarvoid.services.exceptions.DatabaseException;
import com.lunarvoid.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Component
public class QuadradoService {

    @Autowired
    private QuadradoRepository repository;

    public Quadrado findbyId(Long id){
       return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Quadrado> findAll(){
        return repository.findAll();
    }

    public Quadrado insert(Quadrado obj){
        return repository.save(obj);
    }

    public void deleteById(Long id){
        try{
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Quadrado update(Quadrado newobj, Long id){
        try{
            Quadrado lastobj = repository.getReferenceById(id);
            lastobj.setLado(newobj.getLado());
            return repository.save(lastobj);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public Double perimetro(Long id){
        Quadrado obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return obj.getPerimetro();
    }

    public Double area(Long id){
        Quadrado obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return obj.getArea();
    }
    
}
