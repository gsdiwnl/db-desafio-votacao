/**
 * Filename:    ValidEnum.java
 *
 * Description: Implementation of the ValidEnum class.
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
package com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.database.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ValidEnum 
{
    ABLE_TO_VOTE("ABLE_TO_VOTE"),
    UNABLE_TO_VOTE("UNABLE_TO_VOTE");

    private final String value;

    /**
     * ValidEnum
     * 
     * @param voto String
     */
    ValidEnum( String voto ) 
    {
        this.value = voto;
    }

    /**
     * getValue
     * 
     * @return
     */
    @JsonValue
    public String getValue() 
    {
        return value;
    }
}
