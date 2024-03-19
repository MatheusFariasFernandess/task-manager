package org.tcc.api.DTO.input;

import javax.persistence.Column;

public class PrioridadeDTOIn {
    private String codigo;
    private String descricao;
    private String cor;

    public PrioridadeDTOIn() {
    }

    public PrioridadeDTOIn( String codigo, String descricao, String cor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.cor = cor;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
