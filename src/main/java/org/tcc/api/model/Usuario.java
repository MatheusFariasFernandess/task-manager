package org.tcc.api.model;

import org.tcc.api.DTO.input.UsuarioDTOIn;

import javax.persistence.*;

@Entity
@Table(name = "USUARIO")
@SequenceGenerator(name = "SQ_USUARIO",sequenceName = "SQ_USUARIO",allocationSize = 1)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_USUARIO")
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "FOTO")
    private String foto;

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario() {

    }

    public Usuario(UsuarioDTOIn usuario) {
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.foto = usuario.getAnexo() == null ? null : usuario.getAnexo().getNome();
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
