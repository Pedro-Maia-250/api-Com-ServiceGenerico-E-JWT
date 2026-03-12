package com.lunarvoid.dto;

import com.lunarvoid.entities.Circulo;
import com.lunarvoid.enums.TipoFormas;

public class CirculoDTOR {

    private Long id;
    private TipoFormas tipo;
    private Double diametro;

    public CirculoDTOR() {
        this.tipo = TipoFormas.CIRCULO;
    }

    public CirculoDTOR(Long id, Double diametro){
        this.id = id;
        this.diametro = diametro;
        this.tipo = TipoFormas.CIRCULO;
    }

    public CirculoDTOR(Circulo circulo){
        this.id = circulo.getId();
        this.diametro = circulo.getDiametro();
        this.tipo = circulo.getTipo();
    }

    public Circulo toEntity(){
        Circulo circulo = new Circulo(this.diametro);
        return circulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDiametro(Double diametro){
        this.diametro = diametro;
    }

    public Double getDiametro() {
        return diametro;
    }

    public Double getArea(){
        if (diametro == null) return null;
        double raio = diametro / 2;
        return Math.PI * (raio * raio);
    }

    public Double getPerimetro(){
        if (diametro == null) return null;
        return Math.PI * diametro;
    }

    public TipoFormas getTipo() {
        return tipo;
    }  
}
