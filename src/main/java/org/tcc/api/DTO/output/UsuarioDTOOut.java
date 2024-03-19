package org.tcc.api.DTO.output;

import org.tcc.api.model.Usuario;

public class UsuarioDTOOut {
    private Long id;

    private String login;

    private String senha;

    private String foto;

    public UsuarioDTOOut() {
    }

    public UsuarioDTOOut(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.foto = usuario.getFoto() == null ? null : usuario.getFoto();
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
