package com.guilherme.SpringBoot_Marketplace.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String senha;
    private String email;

    public CredenciaisDTO() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
