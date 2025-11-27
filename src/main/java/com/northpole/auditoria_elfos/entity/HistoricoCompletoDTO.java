package com.northpole.auditoria_elfos.entity;

import java.util.List;

public class HistoricoCompletoDTO {

    private String nomeCrianca;
    private boolean bemComportada;
    private List<RevisaoDTO> historico; // A lista de mudanças

    // Construtor
    public HistoricoCompletoDTO(String nomeCrianca, boolean bemComportada, List<RevisaoDTO> historico) {
        this.nomeCrianca = nomeCrianca;
        this.bemComportada = bemComportada;
        this.historico = historico;
    }

    // Getters e Setters
    public String getNomeCrianca() { return nomeCrianca; }
    public void setNomeCrianca(String nomeCrianca) { this.nomeCrianca = nomeCrianca; }

    public boolean isBemComportada() { return bemComportada; }
    public void setBemComportada(boolean bemComportada) { this.bemComportada = bemComportada; }

    public List<RevisaoDTO> getHistorico() { return historico; }
    public void setHistorico(List<RevisaoDTO> historico) { this.historico = historico; }
}