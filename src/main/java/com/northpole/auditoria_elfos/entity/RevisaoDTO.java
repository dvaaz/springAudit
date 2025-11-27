package com.northpole.auditoria_elfos.entity;

import java.time.LocalDateTime;

public class RevisaoDTO {

    private String presente;
    private LocalDateTime data;
		private String elfoResponsavel;

    // Construtor
    public RevisaoDTO(String presente, LocalDateTime data, String elfoResponsavel) {
        this.presente = presente;
        this.data = data;
				this.elfoResponsavel = elfoResponsavel;
    }

    // Getters e Setters
    public String getPresente() { return presente; }
    public void setPresente(String presente) { this.presente = presente; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

		public String getElfoResponsavel() {
				return elfoResponsavel;
		}

		public void setElfoResponsavel(String elfoResponsavel) {
				this.elfoResponsavel = elfoResponsavel;
		}
}