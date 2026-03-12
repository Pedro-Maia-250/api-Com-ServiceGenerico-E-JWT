package com.lunarvoid.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.lunarvoid.dto.DTOA;
import com.lunarvoid.entities.*;
import com.lunarvoid.services.exceptions.DatabaseException;
import com.lunarvoid.services.exceptions.ResourceNotFoundException;

@Component
public class FormaService<T extends Forma<R>,ID, R, A extends DTOA<T>> {

    @Autowired
    private JpaRepository<T,ID> repository;

    public R findbyId(ID id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)).convert();
    }

    public List<R> findAll(){
        return repository.findAll().stream().map((m) -> m.convert()).collect(Collectors.toList());
    }

    public R insert(A DTOA){
        return repository.save(DTOA.save()).convert();
    }

    public void deleteById(ID id){
        try{
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public R update(A DTOA, ID id){
        T t = repository.getReferenceById(id);
        DTOA.update(t);
        return t.convert();
    }

    public Double perimetro(ID id){
        T obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return obj.getPerimetro();
    }

    public Double area(ID id){
        T obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return obj.getArea();
    }

}
