package com.revelecosmeticos.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Libera para TODAS as rotas da API
                .allowedOrigins("*") // Libera para qualquer porta do React (5173, 3000, etc)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Libera os verbos, incluindo o vilão OPTIONS
                .allowedHeaders("*"); // Libera todos os cabeçalhos
    }
}