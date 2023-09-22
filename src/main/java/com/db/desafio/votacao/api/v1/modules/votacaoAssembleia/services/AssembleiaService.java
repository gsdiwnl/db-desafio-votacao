/**
 * Filename:    AssembleiaRepository.java
 *
 * Description: Implementation of the AssembleiaRepository class.
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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.AssembleiaRepository;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Assembleia;

@Service
public class AssembleiaService 
{
    private final Logger logger = LoggerFactory.getLogger( PautaService.class );

    @Autowired
    private AssembleiaRepository assembleiaRepository;
    
    /**
     * getAssembleias
     * 
     * @return List<Assembleia>
     */
    public List<Assembleia> getAssembleias()
    {
        logger.info( "Método: Buscando assembleias" );
        
        return assembleiaRepository.findAll();
    }

    /**
     * addAssembleia
     * 
     * @param assembleia Assembleia 
     * @return Assembleia
     */
    public Assembleia addAssembleia( Assembleia assembleia )
    {
        logger.info( "Método: Registrar nova assembleia" );

        return assembleiaRepository.save( assembleia );
    }
}
