package org.tcc.api.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tcc.api.DTO.input.UsuarioDTOIn;
import org.tcc.api.DTO.output.UsuarioDTOOut;
import org.tcc.api.config.security.TokenService;
import org.tcc.api.exceptions.NegocioException;
import org.tcc.api.exceptions.NotFound;
import org.tcc.api.model.Usuario;
import org.tcc.api.repository.UsuarioRepository;

import javax.transaction.Transactional;

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
    @Transactional
    public UsuarioDTOOut criarUsuario(UsuarioDTOIn usuario){
        checarSeExistsLogin(usuario.getLogin());
        anexoService.salvarArquivo(usuario.getAnexo());
        Usuario usuarioCriado = usuarioRepository.save(new Usuario(usuario));
        return new UsuarioDTOOut(usuarioCriado);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findUsuarioByUserName(username)
                .orElseThrow(()-> new NotFound("Usuario não encontrado"));
    }


    protected Usuario usuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return usuarioRepository.findUsuarioByUserName(authentication.getName())
                .orElseThrow(()->new NotFound("Usuario Não encontrad"));
    }

    public Boolean checarSeExistsLogin(String login){
         if(usuarioRepository.existsUsuario(login)){
             throw new NegocioException("Usuario Já existe");
         };
         return false;
    }
}
