package com.lunarvoid.entities;

import com.lunarvoid.enums.TipoFormas;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_retangulo")
public class Retangulo extends Forma {
    private Double lado1;
    private Double lado2;

    protected Retangulo(){
        super(TipoFormas.RETANGULO);
    }

    public Retangulo(Double lado1, Double lado2){
        super(TipoFormas.RETANGULO);
        setLados(lado1, lado2);
    }

    public void setLados(Double lado1, Double lado2){
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public Double getLado1() {
        return lado1;
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


}
