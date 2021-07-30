package com.guilherme.SpringBoot_Marketplace.dto;

import com.guilherme.SpringBoot_Marketplace.domain.Cidade;

import java.io.Serializable;

public class CidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public CidadeDTO() {

    }

    public CidadeDTO(Cidade obj) {
        id = obj.getId();
        nome = obj.getNome();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
