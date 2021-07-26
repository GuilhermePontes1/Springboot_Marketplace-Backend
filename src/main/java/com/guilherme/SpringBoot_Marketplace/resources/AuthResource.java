package com.guilherme.SpringBoot_Marketplace.resources;

import com.guilherme.SpringBoot_Marketplace.dto.EmailDTO;
import com.guilherme.SpringBoot_Marketplace.security.JWTUtil;
import com.guilherme.SpringBoot_Marketplace.security.UserSS;
import com.guilherme.SpringBoot_Marketplace.services.AuthService;
import com.guilherme.SpringBoot_Marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService service;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST) // protegido por autenticação
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) { // passou no filtro de autorização
        UserSS user = UserService.authenticated(); // aqui pega o usuário logado
        String token = jwtUtil.generateToken(user.getUsername()); // então é gerado um novo token com o usuário logado
        response.addHeader("Authorization", "Bearer " + token); // adiciona o token na resposta da requisição
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
        service.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
