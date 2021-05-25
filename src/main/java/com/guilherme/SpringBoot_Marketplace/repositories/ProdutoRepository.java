package com.guilherme.SpringBoot_Marketplace.repositories;

import java.util.List;
import java.util.Optional;

import com.guilherme.SpringBoot_Marketplace.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guilherme.SpringBoot_Marketplace.domain.Produto;
import org.springframework.transaction.annotation.Transactional;

// REALIZA OPERAÇÃO DE ACESSO A DADOS REFERENTE AO OBJETO CATEGORIA QUE ESTÁ REFERENCIADO COM OBJETO
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
//TODO estudar mais findDistinctByNomeContainingAndCategoriasIn  e Query sendo que findDistinctByNomeContainingAndCategoriasIn é uma opção em vez da query, nesse caso a query sobrepoem  o metodo

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}
