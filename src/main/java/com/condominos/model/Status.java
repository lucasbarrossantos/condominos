package com.condominos.model;

public enum Status {

    VOTADO("Votado"), AGUARDANDO_VOTO("Aguardando voto");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
