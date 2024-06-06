package org.tcc.api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tcc.api.DTO.input.UsuarioDTOIn;
import org.tcc.api.DTO.output.UsuarioDTOOut;
import org.tcc.api.exceptions.NotFound;
import org.tcc.api.model.Usuario;
import org.tcc.api.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService  {
    private final UsuarioRepository usuarioRepository;
    private final AnexoService anexoService;
    private final TokenService tokenService;
    public UsuarioService(UsuarioRepository usuarioRepository, AnexoService anexoService, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.anexoService = anexoService;
        this.tokenService = tokenService;
    }
    public UsuarioDTOOut criarUsuario(UsuarioDTOIn usuario){
        anexoService.salvarArquivo(usuario.getAnexo());
        Usuario usuarioCriado = usuarioRepository.save(new Usuario(usuario));
        return new UsuarioDTOOut(usuarioCriado);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findUsuarioByUserName(username)
                .orElseThrow(()-> new NotFound("Usuario não encontrado"));
    }


    public Usuario usuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return usuarioRepository.findUsuarioByUserName(authentication.getName())
                .orElseThrow(()->new NotFound("Usuario Não encontrad"));
    }
}
