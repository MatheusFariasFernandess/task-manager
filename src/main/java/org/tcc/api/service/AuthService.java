package org.tcc.api.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.tcc.api.DTO.input.UsuarioDTOIn;
import org.tcc.api.DTO.output.UsuarioDTOOut;
import org.tcc.api.config.security.TokenService;
import org.tcc.api.model.Usuario;

import javax.transaction.Transactional;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;
    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    public String login(UsuarioDTOIn usuario){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(usuario.getLogin(),usuario.getSenha());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Usuario usuarioLogado = (Usuario) authenticate.getPrincipal();
        UsuarioDTOOut usuarioDTOOut = new UsuarioDTOOut(usuarioLogado);
        String token = tokenService.createToken(usuarioLogado);
        usuarioDTOOut.setToken(token);
        return token;
    }
}
