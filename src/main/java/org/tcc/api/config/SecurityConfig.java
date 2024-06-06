package org.tcc.api.config;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-ui/* ",
            "/swagger-resources",
            "/swagger-resources/* ",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/* ",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/* ",
            "/swagger-ui/* "
            // other public endpoints of your API may be appended to this array
    };



    public static String[] SWAGGER_URL_PATHS = new String[] { "/swagger-ui/index.html", "/swagger-resources/* ",
            "/swagger-ui.html",
            "/v2/api-docs/* ", "/webjars/ ", "/swaggerfox.js", "/swagger-ui/* "};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csfr->csfr.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(aush->aush.regexMatchers(AUTH_WHITELIST).permitAll())
                .authorizeHttpRequests(auth->auth.regexMatchers(HttpMethod.POST,"/usuario/login").permitAll())
                .authorizeHttpRequests(auth->auth.regexMatchers(HttpMethod.POST,"/usuario/cadastrar").permitAll())
                .authorizeHttpRequests(aush->aush.anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
