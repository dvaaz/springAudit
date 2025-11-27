package com.northpole.auditoria_elfos.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// Essa classe é chamada pelo ENVERS ANTES de lancar qualquer registro na auditoria e injeta o dado do usuario autenticado
public class ElfoRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        ElfoRevisionEntity elfoRevision = (ElfoRevisionEntity) revisionEntity;
        
        // Tenta obter o nome de usuário do contexto de segurança do Spring
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        String username;
        if (authentication != null && authentication.isAuthenticated()) {
            // Captura o nome de usuário (elfo_auditor)
            username = authentication.getName();
        } else {
            // Caso seja uma operação de sistema (startup, testes sem login)
            username = "SISTEMA_NAO_AUTENTICADO"; 
        }

        elfoRevision.setElfoResponsavel(username);
    }
}