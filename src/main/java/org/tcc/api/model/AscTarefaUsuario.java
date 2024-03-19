package org.tcc.api.model;


import javax.persistence.*;

@Entity
@Table(name = "ASC_TAREFA_USUARIO")
@SequenceGenerator(name = "SQ_ASC_TAREFA_USUARIO",sequenceName = "SQ_ASC_TAREFA_USUARIO",allocationSize = 1)
public class AscTarefaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_ASC_TAREFA_USUARIO")
    @Column(name = "ID_TAREFA_USUARIO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_ID_TAREFA")
    private Tarefa tarefa;

    @ManyToOne
    @JoinColumn(name = "FK_USUARIO")
    private Usuario usuario;

    public AscTarefaUsuario() {
    }

    public AscTarefaUsuario(Tarefa tarefa, Usuario usuario) {
        this.tarefa = tarefa;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
