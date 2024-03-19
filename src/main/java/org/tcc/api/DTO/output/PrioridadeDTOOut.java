package org.tcc.api.DTO.output;

import org.tcc.api.model.Prioridade;

public class PrioridadeDTOOut {
    private Long id;
    private String codigo;
    private String cor;
    private String descricao;


    public PrioridadeDTOOut() {
    }

    public PrioridadeDTOOut(Prioridade prioridade) {
        this.id = prioridade.getId();
        this.codigo = prioridade.getCodigo();
        this.cor = prioridade.getCor();
        this.descricao = prioridade.getDescricao();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
