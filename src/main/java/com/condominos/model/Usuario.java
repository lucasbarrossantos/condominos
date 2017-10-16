package com.condominos.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Votacao> votacoes = new ArrayList<>();

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail deve ser informado")
    private String email;

    private String senha;

    @NotBlank(message = "Nome deve ser informada")
    @Column(length = 60)
    private String nome;

    @NotNull(message = "Nível é obrigatório")
    private Integer nivel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Votacao> getVotacoes() {
        return votacoes;
    }

    public void setVotacoes(List<Votacao> votacoes) {
        this.votacoes = votacoes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    @Transient
    public String getNivelUsuario(){

        if (this.nivel == null)
            return null;

        switch (this.nivel){
            case 0:
                return "ADMINISTRADOR";
            case 1:
                return "USUARIO";
        }

        return null;
    }
}
