package org.tcc.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.tcc.api.DTO.input.TarefaDTOIn;
import org.tcc.api.DTO.output.TarefaDTOOut;
import org.tcc.api.exceptions.NotFound;
import org.tcc.api.model.AscTarefaUsuario;
import org.tcc.api.model.Tarefa;
import org.tcc.api.model.Usuario;
import org.tcc.api.repository.AscTarefaUsuarioRepository;
import org.tcc.api.repository.TarefaRepository;
import org.tcc.api.repository.UsuarioRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final AscTarefaUsuarioRepository ascTarefaUsuarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public TarefaService(TarefaRepository tarefaRepository, AscTarefaUsuarioRepository ascTarefaUsuarioRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.tarefaRepository = tarefaRepository;
        this.ascTarefaUsuarioRepository = ascTarefaUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    private Tarefa findTarefaById(Long id)  {
        return  tarefaRepository.findById(id)
                .orElseThrow(()-> new NotFound("Tarefa não encontrada"));
    }

    private Tarefa criarTarefa(TarefaDTOIn dto) throws IOException {
        return tarefaRepository.save(new Tarefa(dto));
    }
    @Transactional
    public void criarTarefaUsuario(List<TarefaDTOIn>dto) throws IOException {
        for(TarefaDTOIn tarefa : dto){
            ascTarefaUsuarioRepository.save(new AscTarefaUsuario(this.criarTarefa(tarefa),usuarioService.usuarioLogado()));
        }
    }
    private void porcentagemTarefaConcluida(TarefaDTOOut tarefa){
        Usuario usuario = new Usuario(1L);

//        Long tarefasTotais = tarefaRepository.quantidadeTarefasDaSemana(usuarioService.usuarioLogado().getId(),tarefa.semanaAtual(),null)  ;
//        Long tarefasConcluidas = tarefaRepository.quantidadeTarefasDaSemana(usuarioService.usuarioLogado().getId(),tarefa.semanaAtual(),Boolean.TRUE);
//
//        tarefa.setPorcentagemConcluida((float) (tarefasConcluidas / tarefasTotais * 100));
    }
    public Page<TarefaDTOOut> listarTarefas(Boolean concluido, String titulo, String descricao, Long prioridade, Pageable paginacao){
        return tarefaRepository.findTarefasByUsuarioId(concluido,usuarioService.usuarioLogado().getId(),titulo,descricao, prioridade,paginacao)
                .map(tarefa-> {
                    TarefaDTOOut tf = new TarefaDTOOut(tarefa);
//                    this.porcentagemTarefaConcluida(tf);
                    return tf;
                });
    }

    @Transactional
    public TarefaDTOOut editarTarefa(Long id,TarefaDTOIn dto) throws Exception {
        Tarefa tarefa = this.findTarefaById(id);
        Tarefa tarefaUpdate = new Tarefa(dto);

        BeanUtils.copyProperties(tarefaUpdate,tarefa,"id","dataCriacao");

        tarefaRepository.save(tarefa);

        return new TarefaDTOOut(tarefa);
    }

    public void marcarConConluidas(TarefaDTOIn tarefasConcluidas)  {
        for(Long tarefa : tarefasConcluidas.getConcluidas()){
            Tarefa tf = this.findTarefaById(tarefa);
            tf.setConcluido(Boolean.TRUE);
            tarefaRepository.save(tf);
        }
    }
    public void excluir(Long id){
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow();
        tarefa.setDataExclusao(LocalDate.now());
        tarefaRepository.save(tarefa);
    }
}
