package org.tcc.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
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
    private final AmqpTemplate rabbitTemplate;
    public UsuarioService(UsuarioRepository usuarioRepository, AnexoService anexoService, TokenService tokenService, RabbitTemplate rabbitTemplate) {
        this.usuarioRepository = usuarioRepository;
        this.anexoService = anexoService;
        this.tokenService = tokenService;
        this.rabbitTemplate = rabbitTemplate;
    }
    @Transactional
//    @RabbitListener(queues = "usuario.criar")
    public UsuarioDTOOut criarUsuario(@Payload UsuarioDTOIn usuario){
        checarSeExistsLogin(usuario.getLogin());
        anexoService.salvarArquivo(usuario.getAnexo());
        Usuario usuarioCriado = usuarioRepository.save(new Usuario(usuario));
        return new UsuarioDTOOut(usuarioCriado);
    }

    public void enviarUsuarioFila(UsuarioDTOIn usuario) throws JsonProcessingException {
        this.rabbitTemplate.convertAndSend("usuario.criar",
                new ObjectMapper().writeValueAsString(usuario));
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
