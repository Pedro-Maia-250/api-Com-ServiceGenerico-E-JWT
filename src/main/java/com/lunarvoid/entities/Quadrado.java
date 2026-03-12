package com.lunarvoid.entities;

import com.lunarvoid.enums.TipoFormas;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_quadrado")
public class Quadrado extends Forma {
    private Double lado;

    protected Quadrado(){
        super(TipoFormas.QUADRADO);
    }

    public Quadrado(Double lado){
        super(TipoFormas.QUADRADO);
        setLado(lado);
    }

    public void setLado(Double lado){
        this.lado = lado;
    }

    public Double getArea(){
        return (lado * lado);
    }

    public Double getPerimetro(){
        return (lado * 4);
    }

    public Double getLado() {
        return lado;
    }
}
