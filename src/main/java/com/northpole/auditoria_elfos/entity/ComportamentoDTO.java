package com.northpole.auditoria_elfos.entity;

public record ComportamentoDTO (
        boolean bemComportada
){
    public ComportamentoDTO ( boolean bemComportada) {

        this.bemComportada = bemComportada;
    }
    @Override
    public boolean bemComportada() {
        return bemComportada;
    }
}
