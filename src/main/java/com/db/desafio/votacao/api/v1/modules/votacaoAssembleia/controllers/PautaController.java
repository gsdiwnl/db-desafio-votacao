/**
 * Filename:    PautaController.java
 *
 * Description: Implementation of the PautaController class.
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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.desafio.votacao.api.v1.misc.exceptions.BadRequestException;
import com.db.desafio.votacao.api.v1.misc.exceptions.NotFoundException;
import com.db.desafio.votacao.api.v1.modules.controllers.Controller;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.controllers.swagger.PautaSwagger;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.PautaResultDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterPautaDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Assembleia;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.services.AssembleiaService;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.services.PautaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( path = Controller.VERSION + "pautas" )
public class PautaController
    extends
        Controller
    implements
        PautaSwagger
{
    @Autowired
    private PautaService pautaService;
    
    @Autowired
    private AssembleiaService assembleiaService;

    /**
     * getPautas
     * 
     * @return ResponseEntity<List<Pauta>>
     */
    @GetMapping()
    public ResponseEntity<List<Pauta>> getPautas()
    {
        List<Pauta> pautas = pautaService.getPautas();

        return ok( pautas );
    }

    /**
     * createPauta
     * 
     * @param pauta Pauta
     * @return ResponseEntity<Pauta>
     */
    @PostMapping()
    public ResponseEntity<Pauta> createPauta( @RequestBody @Valid RegisterPautaDTO pautaDTO )
    {
        Assembleia assembleia = assembleiaService.getAssembleiaById( pautaDTO.getAssembleiaId() );

        if( assembleia == null )
            throw new NotFoundException( "Assembleia not found for ID: #" + pautaDTO.getAssembleiaId() );

        if( pautaDTO.getStartTime().toLocalDate().isBefore( assembleia.getStartDate() )
         || pautaDTO.getEndTime().toLocalDate().isAfter( assembleia.getEndDate() ))
            throw new BadRequestException("Data inicial e final da Pauta devem estar dentro do escopo de datas da Assembleia" );

        Pauta pauta = new Pauta();

        pauta.setDescription( pautaDTO.getDescription() );
        pauta.setVotos( pautaDTO.getVotos() );
        pauta.setStartTime( pautaDTO.getStartTime() );
        pauta.setEndTime( pautaDTO.getEndTime() );
        pauta.setName( pautaDTO.getName() );

        pautaService.addPauta( pauta );

        assembleia.addPauta( pauta );
        assembleiaService.updateAssembleia( assembleia );

        return created( pauta );
    }

    /**
     * getPautaResult
     * 
     * @param pautaId long
     * @return ResponseEntity<PautaResultDTO>
     */
    @Override
    @GetMapping("{pautaId}")
    public ResponseEntity<PautaResultDTO> getPautaResult( @PathVariable("pautaId") long pautaId )
    {
        return ok( pautaService.getPautaResult( pautaId ));
    }
}
