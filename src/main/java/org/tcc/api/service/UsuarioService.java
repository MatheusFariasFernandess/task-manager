package org.tcc.api.service;

import org.springframework.stereotype.Service;
import org.tcc.api.DTO.input.UsuarioDTOIn;
import org.tcc.api.DTO.output.UsuarioDTOOut;
import org.tcc.api.model.Usuario;
import org.tcc.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final AnexoService anexoService;

    public UsuarioService(UsuarioRepository usuarioRepository, AnexoService anexoService) {
        this.usuarioRepository = usuarioRepository;
        this.anexoService = anexoService;
    }
    public UsuarioDTOOut criarUsuario(UsuarioDTOIn usuario){
        anexoService.salvarArquivo(usuario.getAnexo());
        Usuario usuarioCriado = usuarioRepository.save(new Usuario(usuario));
        return new UsuarioDTOOut(usuarioCriado);
    }

}
