package com.lunarvoid.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.lunarvoid.entities.Retangulo;
import com.lunarvoid.repositories.RetanguloRepository;
import com.lunarvoid.services.exceptions.DatabaseException;
import com.lunarvoid.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Component
public class RetanguloService {

    @Autowired
    private RetanguloRepository repository;

    public Retangulo findbyId(Long id){
       return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Retangulo> findAll(){
        return repository.findAll();
    }

    public Retangulo insert(Retangulo obj){
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

    public Retangulo update(Retangulo newobj, Long id){
        try{
            Retangulo lastobj = repository.getReferenceById(id);
            lastobj.setLados(newobj.getLado1(),newobj.getLado2());
            return repository.save(lastobj);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public Double perimetro(Long id){
        Retangulo obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return obj.getPerimetro();
    }

    public Double area(Long id){
        Retangulo obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return obj.getArea();
    }
    
}
