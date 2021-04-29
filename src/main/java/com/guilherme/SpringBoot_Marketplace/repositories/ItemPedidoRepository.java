package com.guilherme.SpringBoot_Marketplace.repositories;

import com.guilherme.SpringBoot_Marketplace.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // REALIZA OPERAÇÃO DE ACESSO A DADOS REFERENTE AO OBJETO CATEGORIA QUE ESTÁ REFERENCIADO COM OBJETO
//CATEGORIA
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

	Optional<ItemPedido> findAllById(Integer id);

}
