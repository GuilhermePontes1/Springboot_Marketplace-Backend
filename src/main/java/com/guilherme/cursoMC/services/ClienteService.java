package com.guilherme.cursoMC.services;
	
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.cursoMC.domain.Cliente;
import com.guilherme.cursoMC.repositories.ClienteRepository;
import com.guilherme.cursoMC.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	

	
	public Cliente consultar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
		new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	
	}
}
