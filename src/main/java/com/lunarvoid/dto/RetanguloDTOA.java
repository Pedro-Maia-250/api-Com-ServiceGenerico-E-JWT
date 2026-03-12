package com.lunarvoid.dto;

import com.lunarvoid.dto.exceptions.DTOAException;
import com.lunarvoid.entities.Retangulo;

public class RetanguloDTOA extends DTOA<Retangulo>{

    private Double lado1;
    private Double lado2;

    public RetanguloDTOA() {
    }

    public RetanguloDTOA(Double lado1, Double lado2){
        setLados(lado1, lado2);
    }

    public Retangulo toEntity(){
        Retangulo Retangulo = new Retangulo(this.lado1, this.lado2);
        return Retangulo;
    }

    public void setLados(Double lado1, Double lado2){
        if(lado1 >= 0 || lado2 >= 0){
            this.lado1 = lado1;
        }else{
            throw new DTOAException("valores invalidos");
        }
    }

    public Double getLado1() {
        return lado1;
    }

    public Double getLado2() {
        return lado2;
    }

    public Retangulo save(){
        return new Retangulo(getLado1(), getLado2());
    }

    public void update(Retangulo obj){
        obj.setLados(getLado1(), getLado2());
    }
}
