/**
 * Filename:    PautaStatusEnum.java
 *
 * Description: Implementation of the PautaStatusEnum class.
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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PautaStatusEnum 
{
    APROVADA("Aprovada"),
    REPROVADA("Reprovada"),
    AGUARDANDO_VOTACAO("Aguardando votação"),
    EMPATADA("Empatada"),
    ANULADA("Anulada");

    private final String value;

    /**
     * PautaStatusEnum
     * 
     * @param status String
     */
    PautaStatusEnum( String status ) 
    {
        this.value = status;
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
