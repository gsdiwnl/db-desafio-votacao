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
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.PautaRepository;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;

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
     * @return List<Pauta>
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
}
