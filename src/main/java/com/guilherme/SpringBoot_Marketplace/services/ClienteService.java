package com.guilherme.SpringBoot_Marketplace.services;
	
import java.util.List;
import java.util.Optional;

import com.guilherme.SpringBoot_Marketplace.domain.enums.Perfil;
import com.guilherme.SpringBoot_Marketplace.dto.ClienteDTO;
import com.guilherme.SpringBoot_Marketplace.dto.ClienteNewDTO;
import com.guilherme.SpringBoot_Marketplace.domain.Cidade;
import com.guilherme.SpringBoot_Marketplace.domain.Cliente;
import com.guilherme.SpringBoot_Marketplace.domain.Endereco;
import com.guilherme.SpringBoot_Marketplace.domain.enums.TipoCliente;
import com.guilherme.SpringBoot_Marketplace.repositories.EnderecoRepository;
import com.guilherme.SpringBoot_Marketplace.security.UserSS;
import com.guilherme.SpringBoot_Marketplace.services.exception.AuthorizationException;
import com.guilherme.SpringBoot_Marketplace.services.exception.DataIntegrityException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.guilherme.SpringBoot_Marketplace.repositories.ClienteRepository;
import com.guilherme.SpringBoot_Marketplace.services.exception.ObjectNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	public Cliente find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
		new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));


	}
	@Transactional// garantia de que será salvo endereço e cliente na mesma transação no banco de dados.
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj; // Faz parte do metodo para inserir um novo cliente junto com endereço

	}

	public Cliente uptade(Cliente obj) {
		Cliente newObj =  find(obj.getId());
		uptadeData(newObj, obj);
		return repo.save(newObj); // Diferença fica na questão do id, quando ele se encontra nulo insere, quando não Atualiza os dados.
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível exclui pois a pedidos relacionados");
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
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
		public Cliente fromDTO(ClienteNewDTO objDto) {
			Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
			Cidade cid = new Cidade(objDto.getCidadeId(),null,null);
			Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(),objDto.getCep(),cli, cid);
			cli.getEnderecos().add(end);
			cli.getTelefones().add(objDto.getTelefone1());
			if (objDto.getTelefone2() != null) {
				cli.getTelefones().add(objDto.getTelefone2());
			}
			if (objDto.getTelefone3() != null) {
				cli.getTelefones().add(objDto.getTelefone3());
			}
		return  cli;
	}



	private void uptadeData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail()); // atualiza para novos valores e busca conforme o usuário
	}
}


