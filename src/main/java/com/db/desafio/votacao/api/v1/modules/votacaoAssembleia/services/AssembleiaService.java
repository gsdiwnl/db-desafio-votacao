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
import com.db.desafio.votacao.api.v1.misc.exceptions.NotFoundException;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.AssembleiaRepository;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterAssembleiaDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Assembleia;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;

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
     * @param assembleiaId long
     * @return Assembleia
     */
    public Assembleia getAssembleiaById( long assembleiaId )
    {
        logger.info( "Método: Buscando assembleia por ID: #" + assembleiaId );
        
        return assembleiaRepository.findById( assembleiaId )
                                    .orElseThrow( () -> new NotFoundException( "Assembleia não encontrada para ID: #" + assembleiaId ));
    }

    /**
     * createAssembleia
     * 
     * @param dto RegisterAssembleiaDTO
     * @return Assembleia
     */
    public Assembleia createAssembleia( RegisterAssembleiaDTO dto )
    {
        logger.info( "Método: Criar nova assembleia" );

        Assembleia assembleia = Assembleia.builder()
                                        .name( dto.getName() )
                                        .description( dto.getDescription() )
                                        .creationDate( dto.getCreationDate() )
                                        .startDate( dto.getStartDate() )
                                        .endDate( dto.getEndDate() )
                                        .build();

        return addAssembleia( assembleia );
    }

    /**
     * isInAssembleiaDateRange
     * 
     * @param assembleia Assembleia
     * @param pauta Pauta
     * @return boolean
     */
    public boolean isInAssembleiaDateRange( Assembleia assembleia, Pauta pauta )
    {
        return pauta.getStartTime().toLocalDate().isBefore( assembleia.getStartDate() )
            || pauta.getEndTime().toLocalDate().isAfter( assembleia.getEndDate() );
    }

    /**
     * addAssembleia
     * 
     * @param assembleia Assembleia 
     * @return Assembleia
     */
    public Assembleia addAssembleia( Assembleia assembleia )
    {
        logger.info( "Método: Salvar nova assembleia" );

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
