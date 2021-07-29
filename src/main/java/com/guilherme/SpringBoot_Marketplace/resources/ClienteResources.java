package com.guilherme.SpringBoot_Marketplace.resources;

import com.guilherme.SpringBoot_Marketplace.dto.ClienteDTO;
import com.guilherme.SpringBoot_Marketplace.dto.ClienteNewDTO;
import com.guilherme.SpringBoot_Marketplace.domain.Cliente;
import com.guilherme.SpringBoot_Marketplace.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes") // encaminha para parte Cliente do código
public class ClienteResources {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)


    public ResponseEntity<Cliente> find(@PathVariable Integer id) {

        Cliente obj = service.find(id); // procura o id a ser mostrada
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/email", method=RequestMethod.GET)
    public ResponseEntity<Cliente> find(@RequestParam(value="value") String email) {
        Cliente obj = service.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> uptade(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
        Cliente obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.uptade(obj);
        return ResponseEntity.noContent().build(); // Metodo para Atualizar Cliente! = PUT!
    }

    @RequestMapping(method = RequestMethod.POST) //RequestBody faz o json ser convertido para objeto java
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) { //@Valid serve para validação do objeto, para se fazer um filtro do que deve ser colocado
        Cliente obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();  // metodo para inserir novos clientes! = POST
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    // Serve para definir quem vai poder realizar essa operação, nesse caso somente "ADMIN"
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // Deletar cliente = DELETE
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    // Serve para definir quem vai poder realizar essa operação, nesse caso somente "ADMIN"
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = service.findAll(); // procura TODOS os ids parra ser mostrados todos clientes
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        // Converte a lista cliente para clienteDto, isso serve para limitar o que  categoria mostra apenas a ela mesma
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

    @RequestMapping(value = "/picture", method = RequestMethod.POST) // metodo para enviarr imagem para clientes service
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
        URI uri = service.uplooadProfilePicture(file);
        return ResponseEntity.created(uri).build();

    }
}


