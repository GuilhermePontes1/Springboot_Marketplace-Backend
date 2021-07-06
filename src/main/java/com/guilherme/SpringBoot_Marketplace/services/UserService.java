package com.guilherme.SpringBoot_Marketplace.services;

import com.guilherme.SpringBoot_Marketplace.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticated() {
       try {
           return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // retorna o usuário logado no sistema.
       } catch (Exception e) {
           return null;
       }

    }

}
