/**
 * Filename:    HttpMessageNotReadableHandler.java
 *
 * Description: Implementation of the HttpMessageNotReadableHandler class.
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
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.db.desafio.votacao.api.v1.misc.Error;

@ControllerAdvice
public class HttpMessageNotReadableHandler
    implements 
        Handler<HttpMessageNotReadableException>
{
    /**
     * handle
     * 
     * @param ex HttpMessageNotReadableException
     * @return ResponseEntity<Error>
     */
    @Override
    @ExceptionHandler( HttpMessageNotReadableException.class )
    public ResponseEntity<Error> handle( HttpMessageNotReadableException ex )
    {
        return response( ex.getMessage(), HttpStatus.BAD_REQUEST );
    }
}
