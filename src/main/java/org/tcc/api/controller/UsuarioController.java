package org.tcc.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    public UsuarioController(UsuarioService usuarioService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/criar")
    public ResponseEntity<UsuarioDTOOut>criarUsuario(@RequestBody UsuarioDTOIn dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTOOut>login(@RequestBody UsuarioDTOIn dto){
        var userNameAndPassword = new UsernamePasswordAuthenticationToken(dto.getLogin(),dto.getSenha());
        var authenticatio = authenticationManager.authenticate(userNameAndPassword);

        var token = tokenService.generateToken((Usuario) authenticatio.getPrincipal());
        UsuarioDTOOut usuarioDTOOut = new UsuarioDTOOut(token);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOOut);
    }
}
