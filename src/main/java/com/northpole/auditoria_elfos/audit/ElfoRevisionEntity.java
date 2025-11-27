package com.northpole.auditoria_elfos.audit;

import jakarta.persistence.*;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serializable;

// Define que esta é a nova entidade de revisão que o Envers deve usar
@Entity
@RevisionEntity(ElfoRevisionListener.class)
@Table(name = "elfo_rev_info") // Use um nome de tabela customizado
public class ElfoRevisionEntity implements Serializable {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@RevisionNumber // Marca este campo como o número da revisão
		private long id;

		@RevisionTimestamp // Marca este campo como o timestamp da revisão
		private long timestamp;

		@Column(name = "elfo_responsavel")
		private String elfoResponsavel;
    // Getters e Setters

		public long getId() {
				return id;
		}

		public void setId(long id) {
				this.id = id;
		}

		public long getTimestamp() {
				return timestamp;
		}

		public void setTimestamp(long timestamp) {
				this.timestamp = timestamp;
		}

		public String getElfoResponsavel() {
				return elfoResponsavel;
		}

		public void setElfoResponsavel(String elfoResponsavel) {
				this.elfoResponsavel = elfoResponsavel;
		}
}