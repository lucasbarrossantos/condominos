package com.condominos.service;

import com.condominos.model.Pauta;
import com.condominos.repository.Pautas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautasService {

    @Autowired
    private Pautas pautas;

    public Pauta salvar(Pauta pauta){
        return pautas.save(pauta);
    }

}
