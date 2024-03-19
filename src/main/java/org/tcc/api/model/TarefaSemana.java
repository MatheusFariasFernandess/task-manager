package org.tcc.api.model;

import javax.persistence.*;

@Entity
@Table(name = "ST_TAREFA_SEMANA")
@SequenceGenerator(name = "SQ_TAREFA_SEMANA",sequenceName = "SQ_TAREFA_SEMANA",allocationSize = 1)
public class TarefaSemana {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_TAREFA_SEMANA")
    @Column(name = "ID_TAREFA_SEMANA")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_USUARIO")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "FK_TAREFA")
    private Tarefa tarefa;


}
