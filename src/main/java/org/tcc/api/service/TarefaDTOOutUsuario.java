package org.tcc.api.service;

import org.springframework.data.domain.Page;
import org.tcc.api.DTO.output.TarefaDTOOut;

import java.util.List;

public class TarefaDTOOutUsuario {
    private Page<TarefaDTOOut> tarefas;
    private Float porcentagemConcluida;

    public Float getPorcentagemConcluida() {
        return porcentagemConcluida;
    }

    public void setPorcentagemConcluida(Float porcentagemConcluida) {
        this.porcentagemConcluida = porcentagemConcluida;
    }

    public Page<TarefaDTOOut> getTarefas() {
        return tarefas;
    }

    public void setTarefas(Page<TarefaDTOOut> tarefas) {
        this.tarefas = tarefas;
    }
}
