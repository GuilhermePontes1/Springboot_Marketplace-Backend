package com.guilherme.SpringBoot_Marketplace.services;

import com.guilherme.SpringBoot_Marketplace.domain.Estado;
import com.guilherme.SpringBoot_Marketplace.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repo;

    public List<Estado> findAll(){
        return repo.findAllByOrderByNome();
    }

}
