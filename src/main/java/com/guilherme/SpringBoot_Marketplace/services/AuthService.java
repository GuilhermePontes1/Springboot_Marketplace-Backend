package com.guilherme.SpringBoot_Marketplace.services;

import com.guilherme.SpringBoot_Marketplace.domain.Cliente;
import com.guilherme.SpringBoot_Marketplace.repositories.ClienteRepository;
import com.guilherme.SpringBoot_Marketplace.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService { // tem o papel de recuperar a senha do usuário
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email) {

        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");

        }
        String  newPass = newPassword();
        cliente.setSenha(pe.encode(newPass));
        emailService.sendNewPasswordEmail(cliente,newPass);

        clienteRepository.save(cliente);

    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i<0; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
    int opt = rand.nextInt(3);
    if (opt == 0 ) { // gera um digito
        return (char) (rand.nextInt(10)+ 48);
    }
    else if (opt == 1) { // gera letra maiuscula
        return (char) (rand.nextInt(26)+ 65);
    }
    else { // gera minuscula
        return (char) (rand.nextInt(26)+ 97);
    }

    }


}
