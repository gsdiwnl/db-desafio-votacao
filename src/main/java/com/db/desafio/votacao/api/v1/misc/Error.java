/**
 * Filename:    Error.java
 *
 * Description: Implementation of the Error class.
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
package com.db.desafio.votacao.api.v1.misc;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class Error 
{
    private int code;
    private String status;
    private String message;

    /**
     * Error
     * 
     * @param httpStatus HttpStatus
     * @param message String
     */
    public Error( HttpStatus httpStatus, String message )
    {
        this.code = httpStatus.value();
        this.message = message;
        this.status = httpStatus.getReasonPhrase();    
    }
}