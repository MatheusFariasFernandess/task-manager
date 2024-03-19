package org.tcc.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tcc.api.DTO.input.PrioridadeDTOIn;
import org.tcc.api.DTO.output.PrioridadeDTOOut;
import org.tcc.api.service.PrioridadeService;

import java.util.List;

@RestController
@RequestMapping("/prioridade")
public class PrioridadeController {
    private final PrioridadeService prioridadeService;

    public PrioridadeController(PrioridadeService prioridadeService) {
        this.prioridadeService = prioridadeService;
    }


    @PostMapping("/criar")
    public ResponseEntity criarPrioridade(@RequestBody PrioridadeDTOIn prioridade){
        prioridadeService.criarPrioridade(prioridade);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity editarPrioridade(@PathVariable("id")Long id,@RequestBody PrioridadeDTOIn dto){
        prioridadeService.editarPrioridade(id,dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PrioridadeDTOOut>>listarPrioridades(){
        return ResponseEntity.status(HttpStatus.OK).body(prioridadeService.listarPrioridades());
    }
}
