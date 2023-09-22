/**
 * Filename:    BadRequestException.java
 *
 * Description: Implementation of the BadRequestException class.
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

public class BadRequestException 
    extends
        RuntimeException
{
    /**
     * BadRequestException
     * 
     * @param message String
     */
    public BadRequestException( String message )
    {
        super( message );
    }

    /**
     * BadRequestException
     * 
     * @param cause Throwable
     */
    public BadRequestException( Throwable cause )
    {
        super( cause );
    }

    /**
     * BadRequestException
     * 
     * @param message String
     * @param cause Throwable
     */
    public BadRequestException( String message, Throwable cause )
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
        return HttpStatus.BAD_REQUEST;
    }
}
