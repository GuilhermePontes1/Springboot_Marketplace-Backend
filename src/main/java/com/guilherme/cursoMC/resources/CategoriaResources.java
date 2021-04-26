package com.guilherme.cursoMC.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.cursoMC.domain.Categoria;
import com.guilherme.cursoMC.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias") // encaminha para parte categoria do código
public class CategoriaResources {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	
	
	public ResponseEntity<?> find(@PathVariable Integer id) {
	
		Categoria obj = service.consultar(id); // procura o id a ser mostrada
	
	
		return ResponseEntity.ok().body(obj);
	}
}
