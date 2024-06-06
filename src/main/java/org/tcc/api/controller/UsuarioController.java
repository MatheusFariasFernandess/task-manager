package org.tcc.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tcc.api.DTO.input.UsuarioDTOIn;
import org.tcc.api.DTO.output.UsuarioDTOOut;
import org.tcc.api.model.Usuario;
import org.tcc.api.service.TokenService;
import org.tcc.api.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    public UsuarioController(UsuarioService usuarioService, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTOOut> login(@RequestBody UsuarioDTOIn dto){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getLogin(),dto.getSenha());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        Usuario usuario = (Usuario) authenticate.getPrincipal();
        UsuarioDTOOut usuarioDTOOut = new UsuarioDTOOut(usuario);
        String token = tokenService.createToken(usuario);
        usuarioDTOOut.setToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOOut);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity criarUsuario(@RequestBody UsuarioDTOIn dto){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.criarUsuario(dto));
    }


}
