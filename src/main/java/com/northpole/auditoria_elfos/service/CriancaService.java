package com.northpole.auditoria_elfos.service;

import com.northpole.auditoria_elfos.entity.Crianca;
import com.northpole.auditoria_elfos.repository.CriancaRepository;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CriancaService {

    private final CriancaRepository criancaRepository;

    public CriancaService(CriancaRepository criancaRepository) {
        this.criancaRepository = criancaRepository;
    }

    // CREATE e UPDATE
    public Crianca salvarCrianca(Crianca crianca) {
        return criancaRepository.save(crianca);
    }
    
    // DELETE
    public void deletarCrianca(Integer id) {
        criancaRepository.deleteById(id);
    }

    // READ e Consulta de Histórico
    public Optional<Crianca> buscarCrianca(Integer id) {
        return criancaRepository.findById(id);
    }
    
    // **Consulta de Auditoria (O Poder do Envers)**
    public Revisions<Integer, Crianca> buscarHistorico(Integer id) {
		    // Retorna todas as versões auditadas para este ID de Criança
		    return criancaRepository.findRevisions(id);
    }
}