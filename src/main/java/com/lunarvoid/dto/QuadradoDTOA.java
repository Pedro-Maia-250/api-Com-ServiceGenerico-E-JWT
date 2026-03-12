package com.lunarvoid.dto;

import com.lunarvoid.dto.exceptions.DTOAException;
import com.lunarvoid.entities.Quadrado;

public class QuadradoDTOA extends DTOA<Quadrado> {

    private Double lado;

    public QuadradoDTOA() {
    }

    public QuadradoDTOA(Double lado){
        setlado(lado);
    }

    public Quadrado toEntity(){
        Quadrado Quadrado = new Quadrado(this.lado);
        return Quadrado;
    }

    public void setlado(Double lado){
        if(lado >= 0){
            this.lado = lado;
        }else{
            throw new DTOAException("lado invalido");
        }
    }

    public Double getlado() {
        return lado;
    }

    public Quadrado save(){
        return new Quadrado(getlado());
    }

    public void update(Quadrado obj){
        obj.setLado(getlado());
    }

}
