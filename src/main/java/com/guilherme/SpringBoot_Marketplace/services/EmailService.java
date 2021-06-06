package com.guilherme.SpringBoot_Marketplace.services;

import com.guilherme.SpringBoot_Marketplace.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendOrderConfirmationEmail (Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendHtmlEmail(MimeMessage msg);
}
