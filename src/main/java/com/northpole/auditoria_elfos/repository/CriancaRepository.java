package com.northpole.auditoria_elfos.repository;


import com.northpole.auditoria_elfos.config.CustomRevision;
import com.northpole.auditoria_elfos.entity.Crianca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

// Estende JpaRepository para operações CRUD normais
// E RevisionRepository para acessar o histórico
@Repository
public interface CriancaRepository
		extends JpaRepository<Crianca, Integer>,
		RevisionRepository<Crianca, Integer, CustomRevision> {

}