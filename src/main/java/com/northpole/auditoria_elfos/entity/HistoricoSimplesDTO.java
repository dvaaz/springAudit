package com.northpole.auditoria_elfos.entity;

import java.time.LocalDateTime;

public class HistoricoSimplesDTO {

    private String nomeCrianca;
    private String presenteDesejado;
    private LocalDateTime dataRevisao;
    
    // Construtor
    public HistoricoSimplesDTO(String nomeCrianca, String presenteDesejado, LocalDateTime dataRevisao) {
        this.nomeCrianca = nomeCrianca;
        this.presenteDesejado = presenteDesejado;
        this.dataRevisao = dataRevisao;
    }

    // Getters e Setters (Necessários sem Lombok)
    public String getNomeCrianca() { return nomeCrianca; }
    public void setNomeCrianca(String nomeCrianca) { this.nomeCrianca = nomeCrianca; }
    
    public String getPresenteDesejado() { return presenteDesejado; }
    public void setPresenteDesejado(String presenteDesejado) { this.presenteDesejado = presenteDesejado; }
    
    public LocalDateTime getDataRevisao() { return dataRevisao; }
    public void setDataRevisao(LocalDateTime dataRevisao) { this.dataRevisao = dataRevisao; }
}