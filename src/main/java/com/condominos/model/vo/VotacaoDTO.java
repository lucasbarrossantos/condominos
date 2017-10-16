package com.condominos.model.vo;

public class VotacaoDTO {

    private Long quantidade;
    private String descricao;

    public VotacaoDTO(Long quantidade, String descricao) {
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
