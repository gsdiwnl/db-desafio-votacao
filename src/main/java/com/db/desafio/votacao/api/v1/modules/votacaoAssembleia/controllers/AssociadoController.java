/**
 * Filename:    AssociadoController.java
 *
 * Description: Implementation of the AssociadoController class.
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

import com.db.desafio.votacao.api.v1.misc.exceptions.NotFoundException;
import com.db.desafio.votacao.api.v1.modules.controllers.Controller;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.controllers.swagger.AssociadoSwagger;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterAssociadoDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Associado;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.services.AssociadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( path = Controller.VERSION + "associados" )
public class AssociadoController 
    extends 
        Controller
    implements
        AssociadoSwagger
{

    @Autowired
    private AssociadoService associadoService;

    /**
     * getAssociados
     * 
     * @return ResponseEntity<List<Associado>>
     */
    @Override
    @GetMapping()
    public ResponseEntity<List<Associado>> getAssociados() 
    {
        return ok( associadoService.getAssociados() );
    }

    /**
     * createAssociado
     * 
     * @param associadoDTO RegisterAssociadoDTO
     * @return ResponseEntity<Associado>
     */
    @Override
    @PostMapping()
    public ResponseEntity<Associado> createAssociado( @RequestBody @Valid RegisterAssociadoDTO associadoDTO ) 
    {
        Associado associado = new Associado();

        associado.setName( associadoDTO.getName() );
        associado.setDocument( associadoDTO.getDocument() );

        return created( associadoService.addAssociado( associado ));
    }

    /**
     * createAssociado
     * 
     * @param document String
     * @return ResponseEntity<Associado>
     */
    @GetMapping("{document}")
    public ResponseEntity<Associado> getAssociadoByDocument( @PathVariable("document") String document )
    {
        Associado associado = associadoService.getAssociadoByDocument( document );

        if( associado == null )
            throw new NotFoundException( "Associado não encontrado para documento: " + document );

        return ok( associado );
    }
}
