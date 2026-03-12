package com.lunarvoid.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.lunarvoid.dto.DTOA;
import com.lunarvoid.entities.*;
import com.lunarvoid.services.exceptions.DatabaseException;
import com.lunarvoid.services.exceptions.ResourceNotFoundException;

public interface FormaService<T extends Forma<R>,ID, R, A extends DTOA<T>> {

    JpaRepository<T,ID> getRepository();

    default R findbyId(ID id){
        return getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException(id)).convert();
    }

    default List<R> findAll(){
        return getRepository().findAll().stream().map((m) -> m.convert()).collect(Collectors.toList());
    }

    default R insert(A DTOA){
        return getRepository().save(DTOA.save()).convert();
    }

    default void deleteById(ID id){
        try{
            getRepository().deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    default R update(A DTOA, ID id){
        T t = getRepository().getReferenceById(id);
        DTOA.update(t);
        return t.convert();
    }

    default Double perimetro(ID id){
        T obj = getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return obj.getPerimetro();
    }

    default Double area(ID id){
        T obj = getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return obj.getArea();
    }

}
