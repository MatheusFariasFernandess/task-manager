package org.tcc.api.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tcc.api.DTO.input.LoginDTOIn;
import org.tcc.api.DTO.input.UsuarioDTOIn;
import org.tcc.api.DTO.output.UsuarioDTOOut;
import org.tcc.api.service.AuthService;
import org.tcc.api.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final AuthService authService;
    private final UsuarioService usuarioService;
    public UsuarioController(AuthService authService, UsuarioService usuarioService) {
        this.authService = authService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTOIn dto){

        return ResponseEntity.status(HttpStatus.OK).body(authService.login(dto));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity criarUsuario(@RequestBody UsuarioDTOIn dto){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.criarUsuario(dto));
    }

    @GetMapping("/exists")
    @ApiOperation(value = "Checa se já existe um usuario com esse login")
    public ResponseEntity<Boolean>existsUsuario(@RequestParam String login){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.checarSeExistsLogin(login));
    }
}
