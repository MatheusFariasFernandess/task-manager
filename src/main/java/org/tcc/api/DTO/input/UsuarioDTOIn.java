package org.tcc.api.DTO.input;

import java.io.Serializable;

public class UsuarioDTOIn implements Serializable {
    private String login;

    private String senha;

    private AnexoDTO anexo;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public AnexoDTO getAnexo() {
        return anexo;
    }

    public void setAnexo(AnexoDTO anexo) {
        this.anexo = anexo;
    }
}
