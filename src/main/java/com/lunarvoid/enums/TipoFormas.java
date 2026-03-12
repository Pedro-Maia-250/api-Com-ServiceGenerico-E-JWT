package com.lunarvoid.enums;

public enum TipoFormas {
    QUADRADO(1),
    CIRCULO(2),
    RETANGULO(3);

    private int code;

    private TipoFormas(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TipoFormas valueOf(int code){
        for (TipoFormas tipo : TipoFormas.values()) {
            if (code == tipo.getCode()){
                return tipo;
            }
        }

        throw new IllegalArgumentException();
    }
}
