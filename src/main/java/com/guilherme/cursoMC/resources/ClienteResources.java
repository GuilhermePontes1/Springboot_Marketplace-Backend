package com.guilherme.cursoMC.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.cursoMC.domain.Cliente;
import com.guilherme.cursoMC.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes") // encaminha para parte Cliente do código
public class ClienteResources {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	
	
	public ResponseEntity<?> find(@PathVariable Integer id) {
	
		Cliente obj = service.consultar(id); // procura o id a ser mostrada
	
	
		return ResponseEntity.ok().body(obj);
	}
}
