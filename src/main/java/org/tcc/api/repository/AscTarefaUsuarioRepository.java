package org.tcc.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tcc.api.model.AscTarefaUsuario;

@Repository
public interface AscTarefaUsuarioRepository extends JpaRepository<AscTarefaUsuario,Long> {
}
