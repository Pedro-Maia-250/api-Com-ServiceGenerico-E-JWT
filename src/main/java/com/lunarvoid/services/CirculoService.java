package com.lunarvoid.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.lunarvoid.entities.Circulo;
import com.lunarvoid.repositories.CirculoRepository;
import com.lunarvoid.services.exceptions.DatabaseException;
import com.lunarvoid.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Component
public class CirculoService {

    @Autowired
    private CirculoRepository repository;

    public Circulo findbyId(Long id){
       return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Circulo> findAll(){
        return repository.findAll();
    }

    public Circulo insert(Circulo obj){
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

    public Circulo update(Circulo newobj, Long id){
        try{
            Circulo lastobj = repository.getReferenceById(id);
            lastobj.setDiametro(newobj.getDiametro());
            return repository.save(lastobj);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public Double perimetro(Long id){
        Circulo obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return obj.getPerimetro();
    }

    public Double area(Long id){
        Circulo obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return obj.getArea();
    }

}
