package com.northpole.auditoria_elfos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configura a política de segurança da aplicação
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		    http
				    // 1. Configuração de Autorização (Permite H2, Autentica o resto)
				    .authorizeHttpRequests((requests) -> requests
						    .requestMatchers("/h2-console/**").permitAll()
						    .anyRequest().authenticated()
				    )

				    // 2. HTTP Basic: Usa o Customizer.withDefaults() (Substitui .httpBasic())
				    .httpBasic(Customizer.withDefaults())

				    // 3. CSRF: Desabilita para facilitar os testes REST (NÃO use em produção)
				    .csrf(csrf -> csrf.disable())

				    // 4. Frame Options: Necessário para o console do H2 funcionar
				    .headers(headers -> headers
						    .frameOptions(frameOptions -> frameOptions.disable()).disable());

		    return http.build();
    }

    // Define um usuário em memória para a demo
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails elfoAuditor = User.withUsername("elfo_folgado")
            .password(encoder.encode("natal123"))
            .roles("ELFO")
            .build();
        
        return new InMemoryUserDetailsManager(elfoAuditor);
    }
}