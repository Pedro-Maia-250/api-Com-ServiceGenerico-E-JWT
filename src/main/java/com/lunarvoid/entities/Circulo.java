package com.lunarvoid.entities;

import com.lunarvoid.enums.TipoFormas;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_circulo")
public class Circulo extends Forma {

    private Double diametro;

    protected Circulo(){
        super(TipoFormas.CIRCULO);
    }

    public Circulo(Double diametro){
        super(TipoFormas.CIRCULO);
        setDiametro(diametro);
    }

    public void setDiametro(Double diametro){
        this.diametro = diametro;
    }

    public Double getArea(){
        Double raio = this.diametro / 2;
        return Math.PI * (raio * raio);
    }

    public Double getPerimetro(){
        return Math.PI * this.diametro;
    }

    public Double getDiametro() {
        return diametro;
    }
    
}
