/**
 * Filename:    CPFService.java
 *
 * Description: Implementation of the CPFService class.
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
public class CPFService 
{
    /**
     * validate
     * 
     * @param CPF String
     * @return Valid
     */
    public Valid validate( String CPF )
    {
        Valid valid = new Valid();

        if( CPF.length() != 11 )
        {
            throw new NotFoundException("Invalid CPF");
        }
        else
        {
            Random random = new Random();
            boolean isValid = random.nextBoolean();

            if( isValid )
            {
                valid.setStatus( ValidEnum.ABLE_TO_VOTE );
            }
            else
            {
                throw new NotFoundException("Invalid CPF");
            }
        }

        return valid;
    }
}
