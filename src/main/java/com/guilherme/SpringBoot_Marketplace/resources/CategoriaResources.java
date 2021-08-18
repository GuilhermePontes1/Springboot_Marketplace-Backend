package com.guilherme.SpringBoot_Marketplace.resources;


import com.guilherme.SpringBoot_Marketplace.dto.CategoriaDTO;
import com.guilherme.SpringBoot_Marketplace.domain.Categoria;
import com.guilherme.SpringBoot_Marketplace.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias") // encaminha para parte categoria do código
public class CategoriaResources {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria obj = service.find(id); // procura o id a ser mostrada
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN')") // Serve para definir quem vai poder realizar essa operação, nesse caso somente "ADMIN"
    @RequestMapping(method = RequestMethod.POST) //RequestBody faz o json ser convertido para objeto java
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) { //@Valid serve para validação do objeto, para se fazer um filtro do que deve ser colocado
        Categoria obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();  // metodo para inserir novas categoria! = POST
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> uptade(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
      Categoria obj = service.fromDTO(objDto);
       obj.setId(id);
        obj = service.uptade(obj);
        return ResponseEntity.noContent().build(); // Metodo para Atualizar Categoria! = PUT!
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // Deletar categorias = DELETE
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> list = service.findAll(); // procura TODOS os ids parra ser mostrados todas categorias
        List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        // Converte a lista categoria para categoriaDto, isso serve para limitar o que  categoria mostra apenas a ela mesma
        // e não a categoria e produtos que nesse caso seria errado devido o requisitos do projeto
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        // Este metodo acima organiza a forma que as paginas vão ser organizadas
        Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj)); // fazendo a transição para lista DTO que será a visualizada pelo usuário
        return ResponseEntity.ok().body(listDto);
    }
}
