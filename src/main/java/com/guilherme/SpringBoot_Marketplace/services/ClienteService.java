package com.guilherme.SpringBoot_Marketplace.services;
	
import java.util.List;
import java.util.Optional;

import com.guilherme.SpringBoot_Marketplace.CategoriaDTO.ClienteDTO;
import com.guilherme.SpringBoot_Marketplace.domain.Cliente;
import com.guilherme.SpringBoot_Marketplace.services.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.guilherme.SpringBoot_Marketplace.repositories.ClienteRepository;
import com.guilherme.SpringBoot_Marketplace.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente consultar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
		new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));


	}
	public Cliente uptade(Cliente obj) {
		Cliente newObj =  consultar(obj.getId());
		uptadeData(newObj, obj);
		return repo.save(newObj); // Diferença fica na questão do id, quando ele se encontra nulo insere, quando não Atualiza os dados.
	}

	public void delete(Integer id) {
		consultar(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível exclui pois a entidades relacionadas");
		}
	}public List<Cliente> findAll() {
		return repo.findAll(); // Metodo para listar todas categorias!
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
		// Filtra as categorias de acordo com o usuário. Essa função funciona através do uso do Page, que faz a paginação
		// funcionalidade adicionada do Spring
	}
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	private void uptadeData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail()); // atualiza para novos valores e busca conforme o usuário
	}
}


