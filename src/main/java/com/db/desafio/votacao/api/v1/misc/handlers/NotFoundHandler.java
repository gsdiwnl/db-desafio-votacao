/**
 * Filename:    NotFoundException.java
 *
 * Description: Implementation of the NotFoundException class.
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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.db.desafio.votacao.api.v1.misc.Error;
import com.db.desafio.votacao.api.v1.misc.exceptions.NotFoundException;

@ControllerAdvice
public class NotFoundHandler
    implements 
        Handler<NotFoundException>
{
    /**
     * handle
     * 
     * @param ex NotFoundException
     * @return ResponseEntity<Error>
     */
    @Override
    @ExceptionHandler( NotFoundException.class )
    public ResponseEntity<Error> handle( NotFoundException ex ) 
    {
        return response( ex.getMessage(), ex.getStatus() );
    }
}
