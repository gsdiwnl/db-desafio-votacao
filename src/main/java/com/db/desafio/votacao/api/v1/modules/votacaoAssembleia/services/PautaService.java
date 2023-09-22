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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.PautaRepository;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;

@Service
public class PautaService
{
    @Autowired
    private PautaRepository pautaRepository;

    /**
     * getPautas
     * 
     * @return List<Pauta>
     */
    public List<Pauta> getPautas()
    {
        return this.pautaRepository.findAll();    
    }

    /**
     * createPauta
     * 
     * @param pauta Pauta
     * @return Pauta
     */
    public Pauta createPauta( Pauta pauta )
    {
        return this.pautaRepository.save( pauta );
    }
}
