package com.northpole.auditoria_elfos.config;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;


// Define qual Listener será usado para preencher os campos
@RevisionEntity(ElfoRevisionListener.class)
@Entity
// Nome da tabela de revisão customizada no BD
@Table(name = "AUDIT_REVISAO_ELFO")
//// Implementamos Comparable<Integer> para satisfazer a restrição de tipo do RevisionRepository
//public class CustomRevision extends DefaultRevisionEntity implements Comparable<CustomRevision>{

public class CustomRevision extends DefaultRevisionEntity {

		private Integer elfoId;

		private String elfoNome;

		// Getters e Setters (já presentes na versão anterior)
		public Integer getElfoId() {
				return elfoId;
		}

		public void setElfoId(Integer elfoId) {
				this.elfoId = elfoId;
		}

		public String getElfoNome() {
				return elfoNome;
		}

		public void setElfoNome(String elfoNome) {
				this.elfoNome = elfoNome;
		}

		// O método compareTo é necessário para satisfazer a interface Comparable.
		// Ele usa o ID da revisão (herdado do DefaultRevisionEntity) para a comparação.
//		@Override
//		public int compareTo(CustomRevision other) {
//				// Obter o ID da revisão. O getId() deve retornar um Integer (wrapper).
//				Integer thisId = this.getId();
//				Integer otherId = other != null ? other.getId() : null;
//
//				// Verifica se o ID desta revisão é nulo.
//				if (thisId == null) {
//						// Se ambos são nulos: 0. Se apenas 'thisId' nulo (menor): -1.
//						return otherId == null ? 0 : -1;
//				}
//				// Se o ID da outra revisão é nulo, e 'thisId' não é (maior): 1.
//				if (otherId == null) {
//						return 1;
//				}
//
//				// Chama o método compareTo da classe Integer.
//				return thisId.compareTo(otherId);
//		}

}