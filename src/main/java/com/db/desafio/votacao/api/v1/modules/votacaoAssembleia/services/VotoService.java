/**
 * Filename:    VotoService.java
 *
 * Description: Implementation of the VotoService class.
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

import com.db.desafio.votacao.api.v1.misc.exceptions.BadRequestException;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.VotoRepository;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.AssociadoStatusEnum;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Associado;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Voto;

@Service
public class VotoService 
{
    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private PautaService pautaService;

    /**
     * getVotos
     * 
     * @return List<Voto>
     */
    public List<Voto> getVotos()
    {
        return votoRepository.findAll();
    }

    /**
     * existsByAssociadoAndPauta
     * 
     * @param associado Associado
     * @param pauta Pauta
     * @return boolean
     */
    public boolean existsByAssociadoAndPauta( Associado associado, Pauta pauta )
    {
        return votoRepository.existsByAssociadoIdAndPautaId( associado.getId(), pauta.getId() );
    }

    /**
     * addVoto
     * 
     * @param voto Voto
     * @return Voto
     */
    public Voto addVoto( Voto voto )
    {
        associadoCanVote( voto );

        Pauta pauta = voto.getPauta();

        pauta.addVoto( voto );
        pautaService.updatePauta( pauta );

        return votoRepository.save( voto );
    }

    /**
     * associadoCanVote
     * 
     * @param voto Voto
     */
    public void associadoCanVote( Voto voto )
    {
        if( pautaService.isExpired( voto.getPauta() ))
        {
            throw new BadRequestException("Pauta (" + voto.getPauta().getName() + ") já foi encerrada");
        }

        if( voto.getAssociado().getStatus() == AssociadoStatusEnum.UNABLE_TO_VOTE )
        {
            throw new BadRequestException("Associado (" + voto.getAssociado().getDocument() + ") não está qualificado para votar");
        }
        
        if( existsByAssociadoAndPauta( voto.getAssociado(), voto.getPauta() ))
        {
            throw new BadRequestException("Associado (" + voto.getAssociado().getDocument() + ") já votou para pauta (" + voto.getPauta().getName() + ")");
        }
    }
}
