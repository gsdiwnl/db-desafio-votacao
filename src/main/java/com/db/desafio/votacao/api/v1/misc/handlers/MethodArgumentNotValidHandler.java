/**
 * Filename:    MethodArgumentNotValidHandler.java
 *
 * Description: Implementation of the MethodArgumentNotValidHandler class.
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

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.db.desafio.votacao.api.v1.misc.Error;

@ControllerAdvice
public class MethodArgumentNotValidHandler
    implements 
        Handler<MethodArgumentNotValidException>
{
    /**
     * handle
     * 
     * @param ex MethodArgumentNotValidException
     * @return ResponseEntity<Error>
     */
    @Override
    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<Error> handle( MethodArgumentNotValidException ex )
    {
        return response( ex.getAllErrors().stream().map( ObjectError::getDefaultMessage ).collect( Collectors.joining(", ") ), HttpStatus.BAD_REQUEST );
    }
}
