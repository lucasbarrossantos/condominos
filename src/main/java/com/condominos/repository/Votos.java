package com.condominos.repository;

import com.condominos.model.Votacao;
import com.condominos.model.vo.VotacaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Votos extends JpaRepository<Votacao, Long> {

    @Query("select new com.condominos.model.vo.VotacaoDTO(count (v.id), c.descricao) from Votacao as v, Categoria as c " +
            "where v.categoria.id = c.id and c.pauta.id = ?1 group by v.categoria.id")
    List<VotacaoDTO> resumoDeVotos(Long pauta_id);

}
