package org.tcc.api.model;

import org.tcc.api.DTO.input.PrioridadeDTOIn;

import javax.persistence.*;

@Entity
@Table(name = "ST_PRIORIDADE")
@SequenceGenerator(name = "SQ_PRIORIDADE",sequenceName = "SQ_PRIORIDADE",allocationSize = 1)
public class Prioridade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_PRIORIDADE")
    @Column(name = "ID_PRIORIDADE")
    private Long id;
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "COR")
    private String cor;


    public Prioridade() {
    }

    public Prioridade(PrioridadeDTOIn dto) {
        this.codigo = dto.getCodigo();
        this.descricao = dto.getDescricao();
        this.cor = dto.getCor();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade(Long id) {
        this.id = id;
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
