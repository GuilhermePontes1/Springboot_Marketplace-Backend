package com.guilherme.SpringBoot_Marketplace.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.SpringBoot_Marketplace.domain.Categoria;

@Repository // REALIZA OPERAÇÃO DE ACESSO A DADOS REFERENTE AO OBJETO CATEGORIA QUE ESTÁ REFERENCIADO COM OBJETO
//CATEGORIA
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> { 

	Optional<Categoria> findAllById(Integer id);

}
