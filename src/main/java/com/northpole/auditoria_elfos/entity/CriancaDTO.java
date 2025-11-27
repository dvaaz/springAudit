//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.northpole.auditoria_elfos.entity;

public record CriancaDTO(String nome, String presente, Boolean bemComportada) {
    public CriancaDTO(String nome, String presente, Boolean bemComportada) {
        this.nome = nome;
        this.presente = presente;
        this.bemComportada = bemComportada;
    }

    public String nome() {
        return this.nome;
    }

    public String presente() {
        return this.presente;
    }

    public Boolean bemComportada() {
        return this.bemComportada;
    }
}
