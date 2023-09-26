/**
 * Filename:    ApplicationContext.java
 *
 * Description: Implementation of the ApplicationContext class.
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

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.db.desafio.votacao.api.v1.modules.controllers.Controller;

public class ApplicationContext 
{
    /**
     * getServerBaseUrl
     * 
     * @return String
     */
    public static String getServerBaseUrl() 
    {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                                            .build()
                                            .toUriString() + "/" + Controller.VERSION;
    }

    /**
     * today
     * 
     * @return LocalDate
     */
    public static LocalDate today()
    {
        return LocalDate.now();
    }

    /**
     * now
     * 
     * @return LocalDateTime
     */
    public static LocalDateTime now()
    {
        return LocalDateTime.now().withNano( 0 );
    }
}
