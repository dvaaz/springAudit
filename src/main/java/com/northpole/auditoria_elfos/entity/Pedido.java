package com.northpole.auditoria_elfos.entity;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Entity
@Audited // **IMPORTANTE: Entidade auditada**
public class Pedido {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer pedidoId;

		private String pedidoNome; // Mapeia para 'presente'

		private String cartinha;

		public Integer getPedidoId() {
				return pedidoId;
		}

		public void setPedidoId(Integer pedidoId) {
				this.pedidoId = pedidoId;
		}

		public String getPedidoNome() {
				return pedidoNome;
		}

		public void setPedidoNome(String pedidoNome) {
				this.pedidoNome = pedidoNome;
		}

		public String getCartinha() {
				return cartinha;
		}

		public void setCartinha(String cartinha) {
				this.cartinha = cartinha;
		}
}