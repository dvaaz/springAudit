package com.northpole.auditoria_elfos.entity;

import java.time.LocalDateTime;

public class RevisaoDTO {

    private String presente;
    private LocalDateTime data;

    // Construtor
    public RevisaoDTO(String presente, LocalDateTime data) {
        this.presente = presente;
        this.data = data;
    }

    // Getters e Setters
    public String getPresente() { return presente; }
    public void setPresente(String presente) { this.presente = presente; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
}