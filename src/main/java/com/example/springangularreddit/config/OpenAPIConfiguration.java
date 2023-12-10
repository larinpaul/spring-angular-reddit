package com.example.springangularreddit.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.mode.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI expenseAPI() {
        return new OpenAPI()
                .info(new Info().title("Reddit Clone API"))
                .description("API for Redin Clone Application")
                .version("v0.0.1")
                .license(new License().name("Apache License Version 2.0").url("http:/programmingtechie.com")))
                .externalDocs(new ExternalDocumentation()
                .description("Expense Tracker Wiki Documentation")
                .utl("https://expensetracker.wiki/docs"));

    }

}
