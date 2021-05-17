package com.guilherme.SpringBoot_Marketplace.resources;

import com.guilherme.SpringBoot_Marketplace.CategoriaDTO.ClienteDTO;
import com.guilherme.SpringBoot_Marketplace.domain.Cliente;
import com.guilherme.SpringBoot_Marketplace.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes") // encaminha para parte Cliente do código
public class ClienteResources {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)


    public ResponseEntity<Cliente> find(@PathVariable Integer id) {

        Cliente obj = service.consultar(id); // procura o id a ser mostrada
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> uptade(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
        Cliente obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.uptade(obj);
        return ResponseEntity.noContent().build(); // Metodo para Atualizar Cliente! = PUT!
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // Deletar categorias = DELETE
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = service.findAll(); // procura TODOS os ids parra ser mostrados todas categorias
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        // Converte a lista categoria para categoriaDto, isso serve para limitar o que  categoria mostra apenas a ela mesma
        // e não a categoria e produtos que nesse caso seria errado devido o requisitos do projeto
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        // Este metodo acima organiza a forma que as paginas vão ser organizadas
        Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj)); // fazendo a transição para lista DTO que será a visualizada pelo usuário
        return ResponseEntity.ok().body(listDto);
    }
}


