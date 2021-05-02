package com.guilherme.SpringBoot_Marketplace.services;

import com.guilherme.SpringBoot_Marketplace.domain.Categoria;
import com.guilherme.SpringBoot_Marketplace.repositories.CategoriaRepository;
import com.guilherme.SpringBoot_Marketplace.services.exception.DataIntegrityException;
import com.guilherme.SpringBoot_Marketplace.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

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
        return repo.save(obj); // Faz parte do metodo para inserir uma nova categoria
    }

    public Categoria uptade(Categoria obj) {
        consultar(obj.getId());
        return repo.save(obj); // Diferença fica na questão do id, quando ele se encontra nulo insere, quando não Atualiza os dados.
    }

    public void delete(Integer id) {
        consultar(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }public List<Categoria> findAll() {
        return repo.findAll(); // Metodo para listar todas categorias!
    }
}
