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
package com.db.desafio.votacao.api.v1.misc.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException 
    extends
        RuntimeException
{
    /**
     * NotFoundException
     * 
     * @param message String
     */
    public NotFoundException( String message )
    {
        super( message );
    }

    /**
     * NotFoundException
     * 
     * @param cause Throwable
     */
    public NotFoundException( Throwable cause )
    {
        super( cause );
    }

    /**
     * NotFoundException
     * 
     * @param message String
     * @param cause Throwable
     */
    public NotFoundException( String message, Throwable cause )
    {
        super( message, cause );
    }

    /**
     * getStatus
     * 
     * @return getStatus
     */
    public HttpStatus getStatus()
    {
        return HttpStatus.NOT_FOUND;
    }
}
