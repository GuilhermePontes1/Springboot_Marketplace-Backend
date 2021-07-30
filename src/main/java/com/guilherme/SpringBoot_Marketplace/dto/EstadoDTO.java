package com.guilherme.SpringBoot_Marketplace.dto;

import com.guilherme.SpringBoot_Marketplace.domain.Estado;

import java.io.Serializable;

public class EstadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public EstadoDTO() {

    }

    public EstadoDTO(Estado obj) {

        obj.getId();
        obj.getNome();
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
