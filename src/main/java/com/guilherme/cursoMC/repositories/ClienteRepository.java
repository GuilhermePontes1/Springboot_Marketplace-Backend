package com.guilherme.cursoMC.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.cursoMC.domain.Cliente;

@Repository // REALIZA OPERAÇÃO DE ACESSO A DADOS REFERENTE AO OBJETO CATEGORIA QUE ESTÁ REFERENCIADO COM OBJETO
//CATEGORIA
public interface ClienteRepository extends JpaRepository<Cliente, Integer> { 

	Optional<Cliente> findAllById(Integer id);

}
