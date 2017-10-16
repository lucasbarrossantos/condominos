package com.condominos.repository;

import com.condominos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Categorias extends JpaRepository<Categoria, Long> {

    List<Categoria> findAllByPautaId(Long id);
}
