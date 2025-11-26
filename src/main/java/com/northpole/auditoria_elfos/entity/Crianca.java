package com.northpole.auditoria_elfos.entity;

import org.hibernate.envers.Audited;
import jakarta.persistence.*;

@Entity
@Audited // **IMPORTANTE: Entidade auditada**
public class Crianca {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer criancaId;

		private String criancaName;

		// TINYINT no BD é mapeado para Boolean em JPA/Java
		private Boolean criancaComportamento;

		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "Pedido_pedidoId")
		private Pedido pedido;

		public Integer getCriancaId() {
				return criancaId;
		}

		public void setCriancaId(Integer criancaId) {
				this.criancaId = criancaId;
		}

		public String getCriancaName() {
				return criancaName;
		}

		public void setCriancaName(String criancaName) {
				this.criancaName = criancaName;
		}

		public Boolean getCriancaComportamento() {
				return criancaComportamento;
		}

		public void setCriancaComportamento(Boolean criancaComportamento) {
				this.criancaComportamento = criancaComportamento;
		}

		public Pedido getPedido() {
				return pedido;
		}

		public void setPedido(Pedido pedido) {
				this.pedido = pedido;
		}
}