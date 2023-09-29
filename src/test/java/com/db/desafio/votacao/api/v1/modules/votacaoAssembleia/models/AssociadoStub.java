/**
 * Filename:    AssociadoStub.java
 *
 * Description: Implementation of the AssociadoStub class.
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

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterAssociadoDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.AssociadoStatusEnum;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Associado;

public class AssociadoStub 
{
    /**
     * createAssociadoWithoutId
     * 
     * @return Associado
     */
    public static Associado createAssociadoWithId()
    {
        Associado associado = Associado.builder()
                                        .id( 1 )
                                        .name("Joseph Ortega")
                                        .document("53347243030")
                                        .status( AssociadoStatusEnum.ABLE_TO_VOTE )
                                        .build();

        return associado;
    }

    /**
     * createAssociadoWithoutId
     * 
     * @return Associado
     */
    public static Associado createAssociadoWithoutId()
    {
        Associado associado = Associado.builder()
                                        .name("Joseph Ortega")
                                        .document("53347243030")
                                        .build();

        return associado;
    }
    
    /**
     * createAssociadoWithWrongCPF
     * 
     * @return Associado
     */
    public static Associado createAssociadoWithWrongCPF()
    {
        Associado associado = Associado.builder()
                                        .name("Joseph Ortega")
                                        .document("53347243031")
                                        .build();

        return associado;
    }
}
