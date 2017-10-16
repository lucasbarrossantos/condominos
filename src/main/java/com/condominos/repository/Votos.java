package com.condominos.repository;

import com.condominos.model.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Votos extends JpaRepository<Votacao, Long> {

}
