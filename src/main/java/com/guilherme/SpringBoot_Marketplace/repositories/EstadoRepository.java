package com.guilherme.SpringBoot_Marketplace.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.SpringBoot_Marketplace.domain.Estado;
import org.springframework.transaction.annotation.Transactional;

@Repository // REALIZA OPERAÇÃO DE ACESSO A DADOS REFERENTE AO OBJETO CATEGORIA QUE ESTÁ REFERENCIADO COM OBJETO
//CATEGORIA
public interface EstadoRepository extends JpaRepository<Estado, Integer> { 

    @Transactional(readOnly = true)
    public List<Estado> findAllByOrderByNome(); // retorna todos os estados ordenados por nome

}
