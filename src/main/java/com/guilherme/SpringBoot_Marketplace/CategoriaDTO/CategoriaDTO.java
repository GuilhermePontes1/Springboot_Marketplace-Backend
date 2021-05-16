package com.guilherme.SpringBoot_Marketplace.CategoriaDTO;

import com.guilherme.SpringBoot_Marketplace.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5,  max =80, message = "tamanho deve ser entre 5 e 80 caracteres")
    private String nome;


    public CategoriaDTO (Categoria obj) {
        id = obj.getId();
        nome = obj.getNome();
    }
    public CategoriaDTO(){

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
