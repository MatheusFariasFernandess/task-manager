package org.tcc.api.DTO.input;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class TarefaDTOIn {
    private String titulo;
    private Long prioridade;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy", shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    private LocalDate dataExclusao;

    private List<Long> concluidas;



    public List<Long> getConcluidas() {
        return concluidas;
    }

    public void setConcluidas(List<Long> concluidas) {
        this.concluidas = concluidas;
    }

    public LocalDate getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(LocalDate dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Long prioridade) {
        this.prioridade = prioridade;
    }
}
