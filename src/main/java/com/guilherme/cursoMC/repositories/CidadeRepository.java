package com.guilherme.cursoMC.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.cursoMC.domain.Cidade;

@Repository // REALIZA OPERAÇÃO DE ACESSO A DADOS REFERENTE AO OBJETO CATEGORIA QUE ESTÁ REFERENCIADO COM OBJETO
//CATEGORIA
public interface CidadeRepository extends JpaRepository<Cidade, Integer> { 

	Optional<Cidade> findAllById(Integer id);

}
