/**
 * Filename:    UnauthorizedHandler.java
 *
 * Description: Implementation of the UnauthorizedHandler class.
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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import db.desafio.votacao.api.v1.misc.Error;
import db.desafio.votacao.api.v1.misc.exceptions.UnauthorizedException;

@ControllerAdvice
public class UnauthorizedHandler 
    implements 
        Handler<UnauthorizedException>
{
    /**
     * handle
     * 
     * @param ex UnauthorizedException
     * @return ResponseEntity<Error>
     */
    @Override
    @ExceptionHandler( UnauthorizedException.class )
    public ResponseEntity<Error> handle( UnauthorizedException ex ) 
    {
        return response( ex.getMessage(), HttpStatus.BAD_REQUEST );
    }
}
