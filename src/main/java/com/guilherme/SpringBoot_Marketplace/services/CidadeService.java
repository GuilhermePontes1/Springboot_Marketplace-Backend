package com.guilherme.SpringBoot_Marketplace.services;

import com.guilherme.SpringBoot_Marketplace.domain.Cidade;
import com.guilherme.SpringBoot_Marketplace.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public List<Cidade>findByEstado(Integer estadoId) { // busca as cidades através da subtabela de cidades que está dentro de estados
        return repo.findCidades(estadoId);
    }

}
