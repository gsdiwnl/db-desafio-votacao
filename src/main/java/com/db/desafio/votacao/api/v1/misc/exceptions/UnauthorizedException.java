/**
 * Filename:    UnauthorizedException.java
 *
 * Description: Implementation of the UnauthorizedException class.
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

public class UnauthorizedException 
    extends 
        RuntimeException
{
    /**
     * UnauthorizedException
     * 
     * @param message String
     */
    public UnauthorizedException( String message )
    {
        super( message );
    }

    /**
     * UnauthorizedException
     * 
     * @param cause Throwable
     */
    public UnauthorizedException( Throwable cause )
    {
        super( cause );
    }

    /**
     * UnauthorizedException
     * 
     * @param message String
     * @param cause Throwable
     */
    public UnauthorizedException( String message, Throwable cause )
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
        return HttpStatus.UNAUTHORIZED;
    }
}
