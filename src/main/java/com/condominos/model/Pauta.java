package com.condominos.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Informe a Descrição")
    @Column(length = 150)
    private String descricao;

    @NotNull(message = "Informe a Data de Início")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "horario_de_inicio")
    private LocalDateTime horarioDeInicio;

    @NotNull(message = "Informe a Data de Encerramento")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "horario_de_encerramento")
    private LocalDateTime horarioDeEncerramento;

    @OneToMany(mappedBy = "pauta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Categoria> categorias = new ArrayList<>();

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
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

    public LocalDateTime getHorarioDeInicio() {
        return horarioDeInicio;
    }

    public void setHorarioDeInicio(LocalDateTime horarioDeInicio) {
        this.horarioDeInicio = horarioDeInicio;
    }

    public LocalDateTime getHorarioDeEncerramento() {
        return horarioDeEncerramento;
    }

    public void setHorarioDeEncerramento(LocalDateTime horarioDeEncerramento) {
        this.horarioDeEncerramento = horarioDeEncerramento;
    }
}
