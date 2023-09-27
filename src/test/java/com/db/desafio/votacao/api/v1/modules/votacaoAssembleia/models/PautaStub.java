/**
 * Filename:    PautaStub.java
 *
 * Description: Implementation of the PautaStub class.
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

import com.db.desafio.votacao.api.v1.config.ApplicationContext;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterPautaDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;

public class PautaStub 
{
    /**
     * createPautaWithId
     * 
     * @return RegisterPautaDTO
     */
    public static Pauta createPautaWithId()
    {
        Pauta pauta = Pauta.builder()
                            .id( 1L )
                            .name( "Pauta originada de TESTES" )
                            .description("Sem descrição" )
                            .startTime( ApplicationContext.now() )
                            .endTime( ApplicationContext.now().plusMinutes( 1 ))
                            .build();

        return pauta;
    }

    /**
     * createPautaWithoutId
     * 
     * @return RegisterPautaDTO
     */
    public static RegisterPautaDTO createPautaWithoutId()
    {
        RegisterPautaDTO pauta = RegisterPautaDTO.builder()
                            .name("Pauta originada de TESTES")
                            .assembleiaId( 1L )
                            .startTime( ApplicationContext.now() )
                            .endTime( ApplicationContext.now().plusMinutes( 1 ))
                            .build();

        return pauta;
    }

    /**
     * createPautaWithWrongDates
     * 
     * @return RegisterPautaDTO
     */
    public static RegisterPautaDTO createPautaWithWrongDates()
    {
        RegisterPautaDTO pauta = RegisterPautaDTO.builder()
                            .name("Pauta originada de TESTES")
                            .assembleiaId( 1L )
                            .startTime( ApplicationContext.now().minusDays( 1 ))
                            .endTime( ApplicationContext.now().plusMinutes( 1 ))
                            .build();

        return pauta;
    }

    /**
     * createPautaWithOutRangeDates
     * 
     * @return RegisterPautaDTO
     */
    public static RegisterPautaDTO createPautaWithOutRangeDates()
    {
        RegisterPautaDTO pauta = RegisterPautaDTO.builder()
                            .name("Pauta originada de TESTES")
                            .assembleiaId( 1L )
                            .startTime( ApplicationContext.now() )
                            .endTime( ApplicationContext.now().plusDays( 1 ))
                            .build();

        return pauta;
    }
}
