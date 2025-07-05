package com.ziirocrm.ziirocrm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                    .addMapping("/**")                     // todas rotas
                    .allowedOrigins("http://localhost:4200") // frontend Angular
                    .allowedMethods("*")                   // GET, POST, PUT, DELETE, PATCH...
                    .allowedHeaders("*")
                    .allowCredentials(true);              // se usar cookies ou auth
            }
        };
    }
}