package com.guilherme.SpringBoot_Marketplace.services;
	
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.SpringBoot_Marketplace.domain.Categoria;
import com.guilherme.SpringBoot_Marketplace.repositories.CategoriaRepository;
import com.guilherme.SpringBoot_Marketplace.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired// SERVE para ser automáticamente ser instanciado pelo spring
	private CategoriaRepository repo;
	
/*	public Categoria buscar (Integer id) {
		Categoria obj = repo.findById(id);

	} Modelo Spring 1.x

	*/
	
	public Categoria consultar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
		new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

		/* recebe um Integer como parametro, para então fazer a busca no banco de dados e retorna o objeto pronto
		 se o objeto estiver vazio, ele retornaria anteriomente um NULL agora ele retorna o id + o tipo do erro além
		 da "suposta categoria" */

	}
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

}
