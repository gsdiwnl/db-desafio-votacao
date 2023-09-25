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

import com.db.desafio.votacao.api.v1.config.ApplicationContext;
import com.db.desafio.votacao.api.v1.misc.exceptions.BadRequestException;
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
     * getAssembleiaById
     * 
     * @return Assembleia
     */
    public Assembleia getAssembleiaById( long id )
    {
        logger.info( "Método: Buscando assembleia por ID: #" + id );
        
        return assembleiaRepository.findById( id ).orElse( null );
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

        this.validDates( assembleia );

        return assembleiaRepository.save( assembleia );
    }
    
    /**
     * updateAssembleia
     * 
     * @param assembleia Assembleia 
     * @return Assembleia
     */
    public void updateAssembleia( Assembleia assembleia )
    {
        logger.info( "Método: Atualizar assembleia" );

        this.validDates( assembleia );

        assembleiaRepository.save( assembleia );
    }

    /**
     * validDates
     * 
     * @param assembleia Assembleia
     */
    public void validDates( Assembleia assembleia )
    {
        logger.info( "Método: Validação de datas de inicio e fim da Assembleia" );

        if( assembleia.getStartDate().isBefore( ApplicationContext.today() ))
            throw new BadRequestException("Assembleia (" + assembleia.getName() + ") não pode iniciar com data anterior ao dia atual");
        
        if( assembleia.getEndDate().isBefore( assembleia.getStartDate() ))
            throw new BadRequestException("Data inicial da assembleia deve ser anterior à data final");
    }
}
