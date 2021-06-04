package com.guilherme.SpringBoot_Marketplace.config;
import com.guilherme.SpringBoot_Marketplace.services.DBService;
import com.guilherme.SpringBoot_Marketplace.services.EmailService;
import com.guilherme.SpringBoot_Marketplace.services.SmtEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean  instantianteDatabase() throws ParseException {

        if(!"create".equals(strategy)) { /* aqui ele passa por um teste de comparação para saber qual valor descrito no application-dev, essa automação oferece o controle
                                         de quando se deve ou não criar outro banco de dados ou atualiza-lo */
            return false;
        }

        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService () {
        return new SmtEmailService();
    }

}
// Classe criada para configuração de banco de dados