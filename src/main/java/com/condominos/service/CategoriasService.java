package com.condominos.service;

import com.condominos.model.Categoria;
import com.condominos.model.Status;
import com.condominos.repository.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriasService {

    @Autowired
    private Categorias categorias;

    public Categoria salvar(Categoria categoria){
        categoria.setStatus(Status.AGUARDANDO_VOTO);
        return categorias.save(categoria);
    }

}
