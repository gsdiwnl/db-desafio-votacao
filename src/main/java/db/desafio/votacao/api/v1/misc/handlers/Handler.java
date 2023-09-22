/**
 * Filename:    Handler.java
 *
 * Description: Implementation of the Handler class.
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

@ControllerAdvice
public interface Handler<T extends Exception>
{
    /**
     * handle
     * 
     * @param ex T
     * @return ResponseEntity<Error>
     */
    public ResponseEntity<Error> handle( T ex );

    /**
     * response
     * 
     * @param message String
     * @param status HttpStatus
     * @return ResponseEntity<Error>
     */
    public default ResponseEntity<Error> response( String message, HttpStatus status )
    {
        return new ResponseEntity<Error>( new Error( status, message ), status );
    }

    /**
     * handleGeneral
     * 
     * @param ex Exception
     * @return ResponseEntity<Error>
     */
    @ExceptionHandler( Exception.class )
    public default ResponseEntity<Error> handleGeneral( Exception ex )
    {
        return response( ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }
}
