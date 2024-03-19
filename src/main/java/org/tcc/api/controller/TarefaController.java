package org.tcc.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tcc.api.DTO.input.TarefaDTOIn;
import org.tcc.api.DTO.output.TarefaDTOOut;
import org.tcc.api.service.TarefaService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<TarefaDTOOut>>listarTarefas(@RequestParam(required = false) Boolean concluido,
                                                           @RequestParam(required = false) String titulo,
                                                           @RequestParam(required = false) String descricao,
                                                           @RequestParam(required = false) Long prioridade,
                                                           Pageable paginacao){
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.listarTarefas(concluido,titulo,descricao,prioridade,paginacao));
    }

    @PostMapping("/criar")
    public ResponseEntity criarTarefa(@RequestBody List<TarefaDTOIn> dto) throws IOException {
        tarefaService.criarTarefaUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTOOut>editarTarefa(@PathVariable("id")Long id,
                                                    @RequestBody TarefaDTOIn tarefa) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.editarTarefa(id,tarefa));
    }

    @PutMapping("/concluidas")
    public ResponseEntity setarTarefasConcluidas(@RequestBody TarefaDTOIn dto)  {
        tarefaService.marcarConConluidas(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity excluirTarefa(@PathVariable("id")Long id)  {
        tarefaService.excluir(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
