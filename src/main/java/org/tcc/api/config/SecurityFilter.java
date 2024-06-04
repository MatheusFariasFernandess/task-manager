package org.tcc.api.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.tcc.api.model.Usuario;
import org.tcc.api.repository.UsuarioRepository;
import org.tcc.api.service.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if(token!=null){
            String subject = tokenService.verigyToken(token);
            Usuario userDetails = usuarioRepository.findUsuarioByUserName(subject)
                    .orElseThrow();

            var logado = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(logado);
        }
        filterChain.doFilter(request,response);
    }


    public String recoverToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if(authorization!=null){
            return authorization.replace("Bearer ","");
        }
        return null;
    }
}
