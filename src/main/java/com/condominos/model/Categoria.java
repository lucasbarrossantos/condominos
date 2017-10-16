package com.condominos.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
@DynamicUpdate
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Descrição deve ser informada!")
    @Column(length = 45)
    private String descricao;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Pauta pauta;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Votacao> votacoes = new ArrayList<>();

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Votacao> getVotacoes() {
        return votacoes;
    }

    public void setVotacoes(List<Votacao> votacoes) {
        this.votacoes = votacoes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
