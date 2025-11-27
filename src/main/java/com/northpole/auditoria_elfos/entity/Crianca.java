//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.northpole.auditoria_elfos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.envers.Audited;

@Entity
@Audited // Marcando que ESSA classe é auditada
public class Crianca {
	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
	)
	private Long id;
	private String nome;
	private String presente;
	private boolean bemComportada;

	public Crianca() {
	}

	public Crianca(String nome, String presente, boolean bemComportada) {
		this.nome = nome;
		this.presente = presente;
		this.bemComportada = bemComportada;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPresente() {
		return this.presente;
	}

	public void setPresente(String presente) {
		this.presente = presente;
	}

	public boolean isBemComportada() {
		return this.bemComportada;
	}

	public void setBemComportada(boolean bemComportada) {
		this.bemComportada = bemComportada;
	}
}
