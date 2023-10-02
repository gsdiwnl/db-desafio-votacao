/**
 * Filename:    ValidacaoDocumentosService.java
 *
 * Description: Implementation of the ValidacaoDocumentosService class.
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
package com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.services;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.db.desafio.votacao.api.v1.misc.exceptions.NotFoundException;
import com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.database.enums.ValidEnum;
import com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.database.models.Valid;

@Service
public class ValidacaoDocumentosService 
{
    /**
     * validateDocument
     * 
     * @param document String
     * @return Valid
     */
    public Valid validateDocument( String document )
    {
        if( new Random().nextBoolean() )
        {
            Valid valid = new Valid();
            
            valid.setStatus( ValidEnum.ABLE_TO_VOTE );

            return valid;
        }
        
        throw new NotFoundException("Invalid document");
    }
}
