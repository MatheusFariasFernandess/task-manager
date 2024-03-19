package org.tcc.api.DTO.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.tcc.api.model.Tarefa;
import org.tcc.api.utils.Utils;

import java.time.LocalDate;


public class TarefaDTOOut {
    private Long id;

    private String titulo;

    private String descricao;

    private PrioridadeDTOOut prioridade;

    @JsonFormat(pattern = "dd/MM/yyyy", shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    private LocalDate dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy", shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    private LocalDate dataExclusao;
    private Boolean concluido = Boolean.FALSE;
    private Float porcentagemConcluida;
    private Long semanaTarefa;

    public TarefaDTOOut() {
    }

    public TarefaDTOOut(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.prioridade = new PrioridadeDTOOut(tarefa.getPriodidade());
        this.dataCriacao = tarefa.getDataCriacao();
        this.dataExclusao = tarefa.getDataExclusao();
        this.concluido = tarefa.getConcluido();
        this.semanaTarefa = tarefa.getSemanaTarefa();
    }

    @JsonIgnoreProperties
    public Long semanaAtual(){
        return Utils.semanaTarefa(LocalDate.now());
    }
    public Long getSemanaTarefa() {
        return semanaTarefa;
    }

    public void setSemanaTarefa(Long semanaTarefa) {
        this.semanaTarefa = semanaTarefa;
    }

    public Float getPorcentagemConcluida() {
        return porcentagemConcluida;
    }

    public void setPorcentagemConcluida(Float porcentagemConcluida) {
        this.porcentagemConcluida = porcentagemConcluida;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
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

    public PrioridadeDTOOut getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeDTOOut prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(LocalDate dataExclusao) {
        this.dataExclusao = dataExclusao;
    }
}
