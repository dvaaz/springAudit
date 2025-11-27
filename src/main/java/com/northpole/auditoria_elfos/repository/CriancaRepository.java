package com.northpole.auditoria_elfos.repository;


import com.northpole.auditoria_elfos.entity.Crianca;
import org.springframework.data.history.Revisions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriancaRepository extends
		JpaRepository<Crianca, Long>, RevisionRepository<Crianca, Long, Long> {
	Revisions<Long, Crianca> findRevisions(Long aLonRevisionRepositoryg);
}
