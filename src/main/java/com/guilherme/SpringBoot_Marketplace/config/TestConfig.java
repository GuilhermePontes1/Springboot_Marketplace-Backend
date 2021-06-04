package com.guilherme.SpringBoot_Marketplace.config;

import com.guilherme.SpringBoot_Marketplace.services.DBService;
import com.guilherme.SpringBoot_Marketplace.services.EmailService;
import com.guilherme.SpringBoot_Marketplace.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean  instantianteDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
