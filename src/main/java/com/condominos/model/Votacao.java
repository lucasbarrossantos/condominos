package com.condominos.model;

import javax.persistence.*;

@Entity
@Table(name = "votacao")
public class Votacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Categoria categoria;

    @ManyToOne
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Votacao{" +
                "id=" + id +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                '}';
    }
}
