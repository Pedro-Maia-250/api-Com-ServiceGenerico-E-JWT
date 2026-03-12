package com.lunarvoid.entities;

import com.lunarvoid.interfaces.FormaInterface;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

import com.lunarvoid.enums.TipoFormas;

@MappedSuperclass
public abstract class Forma implements FormaInterface, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tipo;

    public Forma(TipoFormas tipo){
        setTipo(tipo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoFormas getTipo(){
        return TipoFormas.valueOf(tipo);
    }

    private void setTipo(TipoFormas tipo){
        this.tipo = tipo.getCode();
    }
}
