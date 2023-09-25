/**
 * Filename:    AssembleiaController.java
 *
 * Description: Implementation of the AssembleiaController class.
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.desafio.votacao.api.v1.modules.controllers.Controller;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.controllers.swagger.AssembleiaSwagger;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterAssembleiaDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Assembleia;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.services.AssembleiaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping( path = Controller.VERSION + "assembleias" )
public class AssembleiaController
    extends 
        Controller
    implements 
        AssembleiaSwagger
{
    @Autowired
    private AssembleiaService assembleiaService;

    /**
     * getAssembleias
     * 
     * @return ResponseEntity<List<Assembleia>>
     */
    @Override
    @GetMapping()
    public ResponseEntity<List<Assembleia>> getAssembleias()
    {
        return ok( assembleiaService.getAssembleias() );
    }

    /**
     * createAssembleia
     * 
     * @param assembleiaDTO RegisterAssembleiaDTO
     * @return ResponseEntity<Assembleia>
     */
    @Override
    @PostMapping()
    public ResponseEntity<Assembleia> createAssembleia( @RequestBody @Valid RegisterAssembleiaDTO assembleiaDTO )
    {
        Assembleia assembleia = new Assembleia();

        assembleia.setName( assembleiaDTO.getName() );
        assembleia.setDescription( assembleiaDTO.getDescription() );
        assembleia.setCreationDate( assembleiaDTO.getCreationDate() );
        assembleia.setStartDate( assembleiaDTO.getStartDate() );
        assembleia.setEndDate( assembleiaDTO.getEndDate() );

        return created( assembleiaService.addAssembleia( assembleia ));
    }
}

