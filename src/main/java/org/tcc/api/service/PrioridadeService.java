package org.tcc.api.service;

import org.springframework.stereotype.Service;
import org.tcc.api.DTO.input.PrioridadeDTOIn;
import org.tcc.api.DTO.output.PrioridadeDTOOut;
import org.tcc.api.exceptions.NotFound;
import org.tcc.api.model.Prioridade;
import org.tcc.api.repository.PrioridadeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrioridadeService {
    private final PrioridadeRepository prioridadeRepository;

    public PrioridadeService(PrioridadeRepository prioridadeRepository) {
        this.prioridadeRepository = prioridadeRepository;
    }

    public void criarPrioridade(PrioridadeDTOIn dto){
        prioridadeRepository.save(new Prioridade(dto));
    }

    public void editarPrioridade(Long id,PrioridadeDTOIn dto){
        Prioridade prioridade = prioridadeRepository.findById(id)
                .orElseThrow(()-> new NotFound("Prioriadde não encontrada"));
        prioridade.setCor(dto.getCor());
        prioridade.setDescricao(dto.getDescricao());

        prioridadeRepository.save(prioridade);
    }

    public List<PrioridadeDTOOut> listarPrioridades(){
        return prioridadeRepository.findAll()
                .stream()
                .map(PrioridadeDTOOut::new)
                .collect(Collectors.toList());
    }
}
