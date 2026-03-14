package com.lunarvoid.dto;

import com.lunarvoid.entities.Quadrado;
import com.lunarvoid.enums.TipoFormas;

public class QuadradoDTOR {

    private Long id;
    private TipoFormas tipo;
    private Double lado;

    public QuadradoDTOR() {
        this.tipo = TipoFormas.QUADRADO;
    }

    public QuadradoDTOR(Long id, Double lado){
        this.id = id;
        this.lado = lado;
        this.tipo = TipoFormas.QUADRADO;
    }

    public QuadradoDTOR(Quadrado Quadrado){
        this.id = Quadrado.getId();
        this.lado = Quadrado.getLado();
        this.tipo = Quadrado.getTipo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLado(Double lado){
        this.lado = lado;
    }

    public Double getLado() {
        return lado;
    }

    public Double getArea(){
        return (lado * lado);
    }

    public Double getPerimetro(){
        return (lado * 4);
    }

    public TipoFormas getTipo() {
        return tipo;
    }  
}
