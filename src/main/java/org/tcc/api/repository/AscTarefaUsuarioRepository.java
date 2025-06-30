package org.tcc.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tcc.api.model.AscTarefaUsuario;
import org.tcc.api.projections.AscTarefaUsuarioProjections;

import java.util.List;

@Repository
public interface AscTarefaUsuarioRepository extends JpaRepository<AscTarefaUsuario,Long> {

    @Query("select " +
            "ac.usuario.login as nomeUsuario," +
            "ac.tarefa.descricao as nomeTarefa " +
            "from AscTarefaUsuario ac " +
            "where ac.usuario.id = :id")
    List<AscTarefaUsuarioProjections> findByUsuario(Long id);
}
