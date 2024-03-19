package org.tcc.api.enums;

public enum FileFormat {
    JPG(".jpg"),
    PNG(".PNG");
    private final String descricaoFormato;


    FileFormat(String descricaoFormato) {
        this.descricaoFormato = descricaoFormato;
    }

    public String getDescricaoFormato() {
        return descricaoFormato;
    }
}
