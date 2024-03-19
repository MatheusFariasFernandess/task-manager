package org.tcc.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tcc.api.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Long> {

    @Query("select tf from Tarefa tf " +
            "inner join AscTarefaUsuario tfu on tfu.tarefa.id = tf.id " +
            "inner join Usuario us on tfu.usuario.id = us.id " +
            "inner join Prioridade pr on tf.priodidade = pr " +
            "where tf.dataExclusao is null " +
            "and (:descricao is null or lower(tf.descricao) like concat('%',lower(:descricao),'%') ) " +
            "and (:titulo is null or lower(tf.titulo) like concat('%',lower(:titulo),'%') ) " +
            "and (:prioridade is null or pr.id = :prioridade) " +
            "and (:concluido is null or tf.concluido = :concluido ) " +
            "and us.id = :usuario " +
            "order by tf.descricao asc ")
    Page<Tarefa>findTarefasByUsuarioId(@Param("concluido") Boolean  concluido,
                                       @Param("usuario") Long usuario,
                                       @Param("titulo") String titulo,
                                       @Param("descricao") String descricao,
                                       @Param("prioridade") Long prioridade,
                                       Pageable paginacao);

    @Query("select count(tf) from Tarefa tf " +
            "inner join AscTarefaUsuario tfu on tfu.tarefa = tf " +
            "inner join Usuario us on tfu.usuario = us " +
            "where us.id = :usuario and tf.semanaTarefa = :semana " +
            "and (:concluido is null or tf.concluido = :concluido)")
    Long quantidadeTarefasDaSemana(Long usuario,Long semana,Boolean concluido);
}
