/**
 * Filename:    VotoController.java
 *
 * Description: Implementation of the VotoController class.
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.desafio.votacao.api.v1.modules.controllers.Controller;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.controllers.swagger.VotoSwagger;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterVotoDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Voto;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.services.VotoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping( path = Controller.VERSION + "votos" )
public class VotoController
    extends
        Controller
    implements
        VotoSwagger
{
    @Autowired
    private VotoService votoService;

    @Override
    @GetMapping()
    public ResponseEntity<List<Voto>> getVotos() 
    {
        return ok( votoService.getVotos() );
    }

    @Override
    @PostMapping()
    public ResponseEntity<Voto> createVoto( @RequestBody @Valid RegisterVotoDTO votoDTO ) 
    {    
        return created( votoService.createVoto( votoDTO ));
    }
}
