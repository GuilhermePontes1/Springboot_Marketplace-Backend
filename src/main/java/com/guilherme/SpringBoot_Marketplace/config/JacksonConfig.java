package com.guilherme.SpringBoot_Marketplace.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.SpringBoot_Marketplace.domain.PagamentoComBoleto;
import com.guilherme.SpringBoot_Marketplace.domain.PagamentoComCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration  // CÓDIGO DE EXIGÊNCIA DO JACKSON
    public class JacksonConfig {
    // https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare

        @Bean
        public Jackson2ObjectMapperBuilder objectMapperBuilder() {
            Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
                public void configure(ObjectMapper objectMapper) {
                    objectMapper.registerSubtypes(PagamentoComCartao.class);
                    objectMapper.registerSubtypes(PagamentoComBoleto.class);
                    super.configure(objectMapper);
                }


            };
            return builder;
        }
    }


