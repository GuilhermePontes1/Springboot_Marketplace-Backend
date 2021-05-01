package com.guilherme.SpringBoot_Marketplace.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.guilherme.SpringBoot_Marketplace.domain.Categoria;
import com.guilherme.SpringBoot_Marketplace.services.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import java.net.URI;

@RestController
@RequestMapping(value = "/categorias") // encaminha para parte categoria do código
public class CategoriaResources {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	
	
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
	
		Categoria obj = service.consultar(id); // procura o id a ser mostrada
		return ResponseEntity.ok().body(obj);
	}
	@RequestMapping(method = RequestMethod.POST) //RequestBody faz o json ser convertido para objeto java
	public ResponseEntity<Void> insert (@RequestBody Categoria obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();  // metodo para inserir novas categoria! = POST
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> uptade(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.uptade(obj);
		return ResponseEntity.noContent().build(); // Metodo para Atualizar Categoria!
	}
 }
