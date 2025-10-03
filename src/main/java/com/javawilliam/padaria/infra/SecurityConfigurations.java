package com.javawilliam.padaria.infra;
import com.javawilliam.padaria.infrastructure.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigurations {
    private final UserRepository repository;

    public SecurityConfigurations(UserRepository repository) {
        this.repository = repository;
    }

    // Bean para codificação de senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean para gerenciar autenticação
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configuração de segurança HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // desativa CSRF (para facilitar testes via Postman)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()   // rotas públicas
                        .requestMatchers("/cadastro/**").permitAll() // rotas de cadastro
                        .anyRequest().authenticated()              // o resto precisa de login
                );

        return http.build();
    }
}
