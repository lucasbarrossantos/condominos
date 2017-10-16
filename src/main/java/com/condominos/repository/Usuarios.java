package com.condominos.repository;

import com.condominos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Usuarios extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailIgnoreCase(String email);

    @Query("select c.id from Categoria as c, Votacao as v, Pauta as p, Usuario as u " +
            "where c.pauta.id = p.id and c.id = v.categoria.id and v.usuario.id = u.id and u.id = ?1 and p.id = ?2")
    Long categoriaVotadaPor(Long usuario_id, Long pauta_id);

}
