package com.guilherme.SpringBoot_Marketplace.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.SpringBoot_Marketplace.domain.Cliente;
import org.springframework.transaction.annotation.Transactional;

@Repository // REALIZA OPERAÇÃO DE ACESSO A DADOS REFERENTE AO OBJETO CATEGORIA QUE ESTÁ REFERENCIADO COM OBJETO
//CATEGORIA
public interface ClienteRepository extends JpaRepository<Cliente, Integer> { 

	Optional<Cliente> findAllById(Integer id); // para realização de testes

	@Transactional(readOnly = true) // Ela não necessita ser envolvida como uma transação de banco de dados e optimiza o desempenho do código
	Cliente findByEmail(String email);
}
