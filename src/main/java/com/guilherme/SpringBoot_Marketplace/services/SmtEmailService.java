package com.guilherme.SpringBoot_Marketplace.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtEmailService extends AbstractEmailService{

    @Autowired
    private MailSender mailSender; // automaticamente o framework instancia com todos os dados do email

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);


    @Override
    public void sendEmail(SimpleMailMessage msg) {

            LOG.info("Enviando de email");
            mailSender.send(msg) ; // Enviando email
            LOG.info("Email enviado");
        }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Enviando de email");
        javaMailSender.send(msg) ;
        LOG.info("Email enviado");
    }
}


