/**
 * Filename:    HttpRequestMethodNotSupportedHandler.java
 *
 * Description: Implementation of the HttpRequestMethodNotSupportedHandler class.
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
package com.db.desafio.votacao.api.v1.misc.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.db.desafio.votacao.api.v1.misc.Error;

@ControllerAdvice
public class HttpRequestMethodNotSupportedHandler 
    implements 
        Handler<HttpRequestMethodNotSupportedException>
{
    /**
     * handle
     * 
     * @param ex HttpRequestMethodNotSupportedException
     * @return ResponseEntity<Error>
     */
    @Override
    @ExceptionHandler( HttpRequestMethodNotSupportedException.class )
    public ResponseEntity<Error> handle( HttpRequestMethodNotSupportedException ex )
    {
        return response( ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED );
    }
}
