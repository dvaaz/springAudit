package com.northpole.auditoria_elfos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Simplifica a segurança para este exemplo (apenas autenticação básica)
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .httpBasic(basic -> {});
        return http.build();
    }

    // Configura dois Elfos (usuários) em memória
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("sparkle")
            .password("{noop}senha123") // {noop} significa senha em texto claro
            .roles("ELFO")
            .build();
        
        UserDetails user2 = User.withUsername("frost")
            .password("{noop}senha456")
            .roles("ELFO")
            .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}