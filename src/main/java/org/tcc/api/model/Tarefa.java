package org.tcc.api.model;

import org.tcc.api.DTO.input.TarefaDTOIn;
import org.tcc.api.utils.Utils;

import javax.persistence.*;
import javax.swing.text.Utilities;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Entity
@Table(name = "ST_TAREFA")
@SequenceGenerator(name = "SQ_TAREFA",sequenceName = "SQ_TAREFA",allocationSize = 1)
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_TAREFA")
    @Column(name = "ID_TAREFA")
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DATA_CRIACAO")
    private LocalDate dataCriacao;

    @Column(name = "DATA_EXCLUSAO")
    private LocalDate dataExclusao;

    @Column(name = "CONCLUIDO")
    private Boolean concluido;

    @JoinColumn(name = "PRIORIDADE")
    @ManyToOne
    private Prioridade priodidade;

    @Column(name = "SEMANA_TAREFA")
    private Long semanaTarefa;


    public Tarefa(TarefaDTOIn dto){
        this.titulo = dto.getTitulo();
        this.concluido = Boolean.FALSE;
        this.descricao = dto.getDescricao();
        this.priodidade = new Prioridade(dto.getPrioridade());
        this.dataExclusao = dto.getDataExclusao();
        this.dataCriacao = LocalDate.now();
        this.semanaTarefa = Utils.semanaTarefa(this.getDataCriacao());
    }
    public Tarefa() {
    }

    public Long getSemanaTarefa() {
        return semanaTarefa;
    }

    public void setSemanaTarefa(Long semanaTarefa) {
        this.semanaTarefa = semanaTarefa;
    }

    public LocalDate getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(LocalDate dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade getPriodidade() {
        return priodidade;
    }

    public void setPriodidade(Prioridade priodidade) {
        this.priodidade = priodidade;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }
}
