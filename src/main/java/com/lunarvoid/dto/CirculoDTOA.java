package com.lunarvoid.dto;

import com.lunarvoid.dto.exceptions.DTOAException;
import com.lunarvoid.entities.Circulo;

public class CirculoDTOA extends DTOA<Circulo> {

    private Double diametro;

    public CirculoDTOA() {
    }

    public CirculoDTOA(Double diametro){
        setDiametro(diametro);
    }

    public Circulo toEntity(){
        Circulo circulo = new Circulo(this.diametro);
        return circulo;
    }

    public void setDiametro(Double diametro){
        if(diametro >= 0){
            this.diametro = diametro;
        }else{
            throw new DTOAException("Diametro invalido");
        }
    }

    public Double getDiametro() {
        return diametro;
    }

    public Circulo save(){
        return new Circulo(getDiametro());
    }

    public void update(Circulo obj){
        obj.setDiametro(getDiametro());
    }
}
