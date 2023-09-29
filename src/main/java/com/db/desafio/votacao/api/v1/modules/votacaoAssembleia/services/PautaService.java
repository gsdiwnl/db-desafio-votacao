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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.desafio.votacao.api.v1.config.ApplicationContext;
import com.db.desafio.votacao.api.v1.misc.exceptions.BadRequestException;
import com.db.desafio.votacao.api.v1.misc.exceptions.NotFoundException;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.PautaRepository;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.PautaResultDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterPautaDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.VotoEnum;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Assembleia;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;

@Service
public class PautaService
{
    private final Logger logger = LoggerFactory.getLogger( PautaService.class );

    @Autowired
    private AssembleiaService assembleiaService;

    @Autowired
    private PautaRepository pautaRepository;

    /**
     * getPautas
     * 
     * @return List<Pauta>
     */
    public List<Pauta> getPautas()
    {
        logger.info( "Método: Buscando pautas" );
        
        return this.pautaRepository.findAll();    
    }
    
    /**
     * getPautaById
     * 
     * @param pautaId long
     * @return Pauta
     */
    public Pauta getPautaById( long pautaId )
    {
        logger.info( "Método: Buscando pauta por ID" );
        
        return this.pautaRepository.findById( pautaId )
                                    .orElseThrow( () -> new NotFoundException( "Pauta não encontrada para ID: #" + pautaId ));
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
     * @param dto RegisterPautaDTO
     * @return Pauta
     */
    public Pauta createPauta( RegisterPautaDTO dto )
    {
        logger.info("Método: Criar nova pauta" );

        Assembleia assembleia = assembleiaService.getAssembleiaById( dto.getAssembleiaId() ); 

        Pauta pauta = Pauta.builder()
                            .name( dto.getName() )
                            .description( dto.getDescription() )
                            .votos( dto.getVotos() )
                            .startTime( dto.getStartTime() )
                            .endTime( dto.getEndTime() )
                            .build();

        if( assembleiaService.isInAssembleiaDateRange( assembleia, pauta ))
            throw new BadRequestException("Data inicial e final da Pauta devem estar dentro do escopo de datas da Assembleia" );

        this.addPauta( pauta );

        assembleia.addPauta( pauta );
        assembleiaService.updateAssembleia( assembleia );

        return pauta;
    }

    /**
     * createPauta
     * 
     * @param pauta Pauta
     * @return Pauta
     */
    public Pauta addPauta( Pauta pauta )
    {
        logger.info( "Método: Salvando nova pauta" );

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
        return pauta.getEndTime().isBefore( ApplicationContext.now() );
    }

    /**
     * getPautaResult
     * 
     * @param pautaId long
     * @return PautaResultDTO
     */
    public PautaResultDTO getPautaResult( long pautaId )
    {
        Pauta pauta = this.getPautaById( pautaId ); 
        
        Map<VotoEnum, Long> countedVotos = this.countVotos( pauta );

        return createPautaResult( pauta, countedVotos );
    }

    /**
     * createPautaResult
     * 
     * @param pauta Pauta
     * @param countedVotos Map<VotoEnum, Long>
     * @return PautaResultDTO
     */
    public PautaResultDTO createPautaResult( Pauta pauta, Map<VotoEnum, Long> countedVotos )
    {
        return PautaResultDTO.builder()
                            .pautaId( pauta.getId() )
                            .status( pauta.getStatus() )
                            .total( pauta.getVotos().size() )
                            .approved( countedVotos.get( VotoEnum.SIM ))
                            .rejected( countedVotos.get( VotoEnum.NAO ))
                            .abstention( countedVotos.get( VotoEnum.ABSTENCAO ))
                            .protest( countedVotos.get( VotoEnum.BRANCO ))
                            .build();
    }

    /**
     * countVotes
     * 
     * @param pauta Pauta
     * @return Map<VotoEnum, Long>
     */
    public Map<VotoEnum, Long> countVotos( Pauta pauta )
    {
        return Arrays.stream( VotoEnum.values() )
                    .collect( Collectors.toMap( (voto) -> voto, 
                                                (voto) -> pauta.getVotos()
                                                               .stream()
                                                               .filter( (v) -> 
                                                                         v.getVoto() == voto )
                                                               .count() ));
    }

    /**
     * validDates
     * 
     * @param pauta Pauta
     */
    public void validDates( Pauta pauta )
    {
        logger.info( "Método: Validação de datas de inicio e fim da Pauta" );

        if( pauta.getStartTime().toLocalDate().isBefore( ApplicationContext.today()))
            throw new BadRequestException("Pauta (" + pauta.getName() + ") não pode iniciar com data anterior a atual");
        
        if( pauta.getEndTime().isBefore( pauta.getStartTime() ))
            throw new BadRequestException("Horário inicial da pauta deve ser anterior ao horário final");
    }
}
