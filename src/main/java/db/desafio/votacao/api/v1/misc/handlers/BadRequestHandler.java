/**
 * Filename:    BadRequestHandler.java
 *
 * Description: Implementation of the BadRequestHandler class.
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
package db.desafio.votacao.api.v1.misc.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import db.desafio.votacao.api.v1.misc.Error;
import db.desafio.votacao.api.v1.misc.exceptions.BadRequestException;

@ControllerAdvice
public class BadRequestHandler 
    implements 
        Handler<BadRequestException>
{
    /**
     * handle
     * 
     * @param ex BadRequestException
     * @return ResponseEntity<Error>
     */
    @Override
    @ExceptionHandler( BadRequestException.class )
    public ResponseEntity<Error> handle( BadRequestException ex ) 
    {
        return response( ex.getMessage(), ex.getStatus() );
    }
}
