package com.guilherme.SpringBoot_Marketplace.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guilherme.SpringBoot_Marketplace.domain.Cidade;
import org.springframework.transaction.annotation.Transactional;

@Repository // REALIZA OPERAÇÃO DE ACESSO A DADOS REFERENTE AO OBJETO CATEGORIA QUE ESTÁ REFERENCIADO COM OBJETO
//CATEGORIA
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Transactional(readOnly=true)
    @Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome") // faz a busca dentro do banco de dados
    public List<Cidade> findCidades(@Param("estadoId") Integer estado_id);

}
