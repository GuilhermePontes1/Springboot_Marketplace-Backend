package com.guilherme.SpringBoot_Marketplace.services;

import com.guilherme.SpringBoot_Marketplace.dto.CategoriaDTO;
import com.guilherme.SpringBoot_Marketplace.domain.Categoria;
import com.guilherme.SpringBoot_Marketplace.repositories.CategoriaRepository;
import com.guilherme.SpringBoot_Marketplace.services.exception.DataIntegrityException;
import com.guilherme.SpringBoot_Marketplace.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired// SERVE para ser automáticamente ser instanciado pelo spring
    private CategoriaRepository repo;
	
/*	public Categoria buscar (Integer id) {
		Categoria obj = repo.findById(id);

	} Modelo Spring 1.x

	*/

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

		/* recebe um Integer como parametro, para então fazer a busca no banco de dados e retorna o objeto pronto
		 se o objeto estiver vazio, ele retornaria anteriomente um NULL agora ele retorna o id + o tipo do erro além
		 da "suposta categoria" */

    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj); // Faz parte do metodo para inserir uma nova categoria

    }

    public Categoria uptade(Categoria obj) {
        Categoria newObj =  find(obj.getId());
        uptadeData(newObj, obj);
        return repo.save(newObj); // Diferença fica na questão do id, quando ele se encontra nulo insere, quando não Atualiza os dados.
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }public List<Categoria> findAll() {
        return repo.findAll(); // Metodo para listar todas categorias!
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
        // Filtra as categorias de acordo com o usuário. Essa função funciona através do uso do Page, que faz a paginação
        // funcionalidade adicionada do Spring
    }
    public Categoria fromDTO(CategoriaDTO objDto) {
        return new Categoria(objDto.getId(),objDto.getNome());
    }
    private void uptadeData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
         // atualiza para novos valores e busca conforme o usuário
    }

}
