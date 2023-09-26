/**
 * Filename:    AssembleiaStub.java
 *
 * Description: Implementation of the AssembleiaStub class.
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
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Assembleia;

public class AssembleiaStub
{

    /**
     * createAssembleiaWithoutId
     * 
     * @return Assembleia
     */
    public static Assembleia createAssembleiaWithoutId()
    {
        Assembleia assembleia = Assembleia.builder()
                                            .name("Assembleia originada de TESTES")
                                            .startDate( ApplicationContext.today() )
                                            .endDate( ApplicationContext.today().plusDays( 1 ))
                                            .build();

        return assembleia;
    }
    
    /**
     * createAssembleiaWithWrongDates
     * 
     * @return Assembleia
     */
    public static Assembleia createAssembleiaWithWrongDates()
    {
        Assembleia assembleia = Assembleia.builder()
                                            .name("Assembleia originada de TESTES")
                                            .startDate( ApplicationContext.today().minusDays( 1 ))
                                            .endDate( ApplicationContext.today() )
                                            .build();
    
        return assembleia;
    }
}
