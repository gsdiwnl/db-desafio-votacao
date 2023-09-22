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
package db.desafio.votacao.api.v1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import db.desafio.votacao.api.v1.controllers.util.Controller;
import db.desafio.votacao.api.v1.models.Pauta;
import db.desafio.votacao.api.v1.services.PautaService;

@RestController
@RequestMapping( path = Controller.VERSION + "pautas" )
public class PautaController
    extends
        Controller
{
    @Autowired
    private PautaService pautaService;

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
    public ResponseEntity<Pauta> createPauta( @RequestBody Pauta pauta )
    {
        return new ResponseEntity<>( pautaService.createPauta( pauta ), HttpStatus.CREATED );
    }
}
