/**
 * Filename:    PautaService.java
 *
 * Description: Implementation of the PautaService class.
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
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.PautaRepository;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.PautaResultDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.VotoEnum;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Voto;

@Service
public class PautaService
{
    private final Logger logger = LoggerFactory.getLogger( PautaService.class );

    @Autowired
    private PautaRepository pautaRepository;

    /**
     * getPautas
     * 
     * @return List<Pauta>
     */
    public List<Pauta> getPautas()
    {
        logger.info("Método: Buscando pautas");
        
        return this.pautaRepository.findAll();    
    }
    
    /**
     * getPauta
     * 
     * @param id long
     * @return Pauta
     */
    public Pauta getPauta( long id )
    {
        logger.info("Método: Buscando pauta por ID: #" + id );
        
        return this.pautaRepository.findById( id )
                                    .orElse( null );
    }

    /**
     * updatePauta
     * 
     * @param pauta Pauta
     */
    public void updatePauta( Pauta pauta )
    {
        logger.info("Método: Atualizar pauta" );

        this.validDates( pauta );

        this.pautaRepository.save( pauta );
    }

    /**
     * createPauta
     * 
     * @param pauta Pauta
     * @return Pauta
     */
    public Pauta addPauta( Pauta pauta )
    {
        logger.info("Método: Cadastrando nova pauta: " + pauta.getName() );

        this.validDates( pauta );

        return this.pautaRepository.save( pauta );
    }

    /**
     * isExpired
     * 
     * @param pauta Pauta
     * @return boolean
     */
    public boolean isExpired( Pauta pauta )
    {
        if( pauta.getEndTime().isBefore( ApplicationContext.now() ))
        {
            return true;
        }

        return false;
    }

    /**
     * getPautaResult
     * 
     * @param pautaId long
     * @return PautaResultDTO
     */
    public PautaResultDTO getPautaResult( long pautaId )
    {
        Pauta pauta = this.getPauta( pautaId ); 

        if( pauta == null )
            throw new NotFoundException( "Pauta não encontrada para ID: #" + pautaId );
            
        PautaResultDTO result = new PautaResultDTO();

        long approved = 0;
        long rejected = 0;
        long abstention = 0;
        long protest = 0;

        for( Voto voto : pauta.getVotos() )
        {
            if( voto.getVoto().equals( VotoEnum.SIM ))
            {
                approved++;
            }
            else if( voto.getVoto().equals( VotoEnum.NAO ))
            {
                rejected++;
            }
            else if( voto.getVoto().equals( VotoEnum.ABSTENCAO ))
            {
                abstention++;
            }
            else if( voto.getVoto().equals( VotoEnum.BRANCO ))
            {
                protest++;
            }
        }

        result.setPautaId( pautaId );
        result.setDescription( pauta.getDescription() );
        result.setApproved( approved );
        result.setRejected( rejected );
        result.setAbstention( abstention );
        result.setProtest( protest );
        result.setTotal( pauta.getVotos().size() );
        result.setStatus( pauta.getStatus() );

        return result;
    }

    /**
     * validDates
     * 
     * @param pauta Pauta
     */
    public void validDates( Pauta pauta )
    {
        logger.info( "Método: Validação de datas de inicio e fim da Assembleia" );

        if( pauta.getStartTime().toLocalDate().isBefore( ApplicationContext.today()))
            throw new BadRequestException("Pauta (" + pauta.getName() + ") não pode iniciar com data anterior a atual");
        
        if( pauta.getEndTime().isBefore( pauta.getStartTime() ))
            throw new BadRequestException("Horário inicial da pauta deve ser anterior ao horário final");
    }
}
