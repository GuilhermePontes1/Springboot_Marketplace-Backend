package com.guilherme.SpringBoot_Marketplace.config;


import com.guilherme.SpringBoot_Marketplace.security.JWTAuthenticationFilter;
import com.guilherme.SpringBoot_Marketplace.security.JWTAuthorizationFilter;
import com.guilherme.SpringBoot_Marketplace.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtutil;

    private static final String[] PUBLIC_MATHCHERS = {
            "/h2-console/**"

    };
    private static final String[] PUBLIC_MATHCHERS_GET = { // ESSE caminho serve para permitir somente a recuperação de dados e não sua modificação
            "/h2-console/**",
            "/produtos/** ",
            "/categorias/** ",

    };

    private static final String[] PUBLIC_MATHCHERS_POST = {
            "/clientes/",
            "/auth/forgot/**"

    };
    @Override
    protected void configure(HttpSecurity http) throws  Exception {

        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable(); // liberar o test para o h2
        }

        http.cors().and().csrf().disable(); // desabilitando ataques crsf já que o sistema não armazena sessão
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, PUBLIC_MATHCHERS_POST).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATHCHERS_GET).permitAll() //todos os caminhos que tiveem get serão permitidos( serve para que não seja alterado)
                .antMatchers(PUBLIC_MATHCHERS).permitAll() //todos os caminhos que tiverem nesse vetor serão permitidos
        .anyRequest().authenticated(); // os que não tiverem, peça autenticação
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtutil)); // adicionar filtros
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtutil, userDetailsService));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Assegura que o backend não vai criar sessão de usuário

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues()); // permitindo acesso a endpoints com
                                                                                                             // configuração básica(Temporário)
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder () {
    return new BCryptPasswordEncoder();
    }


    }

