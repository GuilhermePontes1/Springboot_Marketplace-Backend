package com.guilherme.SpringBoot_Marketplace.resources;


import com.guilherme.SpringBoot_Marketplace.domain.Categoria;
import com.guilherme.SpringBoot_Marketplace.domain.Pedido;
import com.guilherme.SpringBoot_Marketplace.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos") // encaminha para parte categoria do código
public class PedidoResources {

    @Autowired
    private PedidoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)


    public ResponseEntity<Pedido> find(@PathVariable Integer id) {

        Pedido obj = service.consultar(id); // procura o id a ser mostrada


        return ResponseEntity.ok().body(obj);
    }
}
