/**
 * Filename:    SwaggerConfig.java
 *
 * Description: Implementation of the SwaggerConfig class.
 *
 * Revision:    1.0
 *
 * Changed by:  gd
 *
 * Author:      Gabriel Dullius
 * Email:       dulliusgabriel@gmail.com
 *
 * Copyright of the computer program(s) contained herein
 * is of intellectual property of the studying for the voting challenge by DB.
 * The program(s) may be used and/or copied with permission.
 * Challenge: https://github.com/dbserver/desafio-votacao
 *
 */
package com.db.desafio.votacao.api.v1.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig 
{
    public static final String SUCCESS_MESSAGE = "Operação bem sucedida";
    public static final String NOT_FOUND_MESSAGE = "Objeto não encontrado com informações enviadas";
    public static final String BAD_REQUEST_MESSAGE = "Dados enviados inválidos, confira documentação";
    public static final String FORBIDDEN_MESSAGE = "Recurso já existente para atributos enviados";

    @Bean
    OpenAPI apiInfo() 
    {
        return new OpenAPI()
                .info( new Info()
                        .title( "Desafio votação" )
                        .description("API utilizada para resolução do Desafio de Votação em SpringBoot")
                        .contact( new Contact()
                                    .name( "Gabriel Dullius" )
                                    .url( "http://github.com/gsdiwnl" ))
                                    .version("1.0.0")
                                    .license( new License() )
                                    .termsOfService("") )
                .externalDocs( new ExternalDocumentation()
                                .description("Repositório da API no GitHub")
                                .url("https://github.com/gsdiwnl/db-desafio-votacao"))
                                .addServersItem( new Server()
                                                    .url( "http://localhost:8080") );
    }
}