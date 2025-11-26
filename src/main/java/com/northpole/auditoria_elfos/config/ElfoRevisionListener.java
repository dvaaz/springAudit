package com.northpole.auditoria_elfos.config;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
// Essa classe está aqui preenchida com dados do Elfo para agilizar o Login
public class ElfoRevisionListener implements RevisionListener {

		@Override
		public void newRevision(Object revisionEntity) {
				CustomRevision customRevision = (CustomRevision) revisionEntity;
        // Simulação de obtenção do usuário logado (Elfo)
        // Em um projeto real, isso viria do Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String elfoUsername = authentication.getName();

            // Lógica fictícia para obter ID e Nome de um Elfo
            if (elfoUsername.equals("sparkle")) {
                customRevision.setElfoId(101);
                customRevision.setElfoNome("Sparkle o Duende Purpurinado");
            } else if (elfoUsername.equals("frost")) {
                customRevision.setElfoId(102);
                customRevision.setElfoNome("Frost o Mestre dos Sorvetes");
            } else { // caso algo de errado
                 customRevision.setElfoId(0);
                 customRevision.setElfoNome("Sistema/Anonimo");
            }
        } else {
            customRevision.setElfoId(0);
            customRevision.setElfoNome("Sistema/Anonimo");
        }
    }
}