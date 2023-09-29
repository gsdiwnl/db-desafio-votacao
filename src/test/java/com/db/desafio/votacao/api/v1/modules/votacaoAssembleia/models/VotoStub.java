/**
 * Filename:    VotoStub.java
 *
 * Description: Implementation of the VotoStub class.
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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.models;

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterVotoDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.VotoEnum;

public class VotoStub 
{
    /**
     * createRegisterVoto
     * 
     * @return RegisterVotoDTO
     */
    public static RegisterVotoDTO createRegisterVoto()
    {
        RegisterVotoDTO votoDTO = RegisterVotoDTO.builder()
                                                .documentAssociado("53347243030")
                                                .pautaId( 1L )
                                                .voto( VotoEnum.SIM )
                                                .build();

        return votoDTO;
    }
}
