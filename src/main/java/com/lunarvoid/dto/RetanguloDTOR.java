package com.lunarvoid.dto;

import com.lunarvoid.entities.Retangulo;
import com.lunarvoid.enums.TipoFormas;

public class RetanguloDTOR {

    private Long id;
    private TipoFormas tipo;
    private Double lado1;
    private Double lado2;

    public RetanguloDTOR() {
        this.tipo = TipoFormas.RETANGULO;
    }

    public RetanguloDTOR(Long id, Double lado1){
        this.id = id;
        this.lado1 = lado1;
        this.lado1 = lado2;
        this.tipo = TipoFormas.RETANGULO;
    }

    public RetanguloDTOR(Retangulo Retangulo){
        this.id = Retangulo.getId();
        this.lado1 = Retangulo.getLado1();
        this.tipo = Retangulo.getTipo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLado1(Double lado1){
        this.lado1 = lado1;
    }

    public Double getLado1() {
        return lado1;
    }

    public void setLado2(Double lado1){
        this.lado1 = lado2;
    }

    public Double getLado2() {
        return lado2;
    }

    public Double getArea(){
        return (this.lado1 * this.lado2);
    }

    public Double getPerimetro(){
        return ((this.lado1 * 2) + (this.lado2 * 2));
    }

    public TipoFormas getTipo() {
        return tipo;
    }  
}
