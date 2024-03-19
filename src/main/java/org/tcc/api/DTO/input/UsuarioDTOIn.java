package org.tcc.api.DTO.input;

public class UsuarioDTOIn {
    private String login;

    private String senha;

    private AnexoDTO anexo;

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
