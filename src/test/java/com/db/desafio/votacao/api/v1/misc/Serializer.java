/**
 * Filename:    Serializer.java
 *
 * Description: Implementation of the Serializer class.
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
package com.db.desafio.votacao.api.v1.misc;

import com.db.desafio.votacao.api.v1.misc.exceptions.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Serializer 
{
    /**
     * json
     * 
     * @param obj T
     * @return String
     */
    public static <T> String json( final T objectToConvert )
    {
        try 
        {
            ObjectMapper objectMapper = new ObjectMapper();
            
            objectMapper.registerModule( new JavaTimeModule() );

            return objectMapper.writeValueAsString( objectToConvert );
        } 
        catch( JsonProcessingException ex )
        {
            throw new BadRequestException( ex.getMessage() );
        }
    }
}
