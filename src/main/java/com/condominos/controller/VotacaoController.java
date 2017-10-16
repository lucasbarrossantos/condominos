package com.condominos.controller;

import com.condominos.model.Categoria;
import com.condominos.model.Status;
import com.condominos.model.Votacao;
import com.condominos.repository.Categorias;
import com.condominos.repository.Votos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/votos")
public class VotacaoController {

    @Autowired
    private Votos votos;

    @Autowired
    private Categorias categorias;

    @PostMapping(value = "/novo", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<?> salvar(@RequestBody Votacao votacao) {
        atualizarCategoria(votacao);
        votacao = votos.save(votacao);
        return ResponseEntity.ok(votacao);
    }

    private void atualizarCategoria(Votacao votacao) {
        Categoria categoria = categorias.findOne(votacao.getCategoria().getId());
        categoria.setStatus(Status.VOTADO);
        categorias.save(categoria);
    }

}
