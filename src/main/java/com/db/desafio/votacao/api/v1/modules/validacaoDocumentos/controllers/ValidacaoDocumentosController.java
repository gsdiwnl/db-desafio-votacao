/**
 * Filename:    ValidacaoDocumentosController.java
 *
 * Description: Implementation of the ValidacaoDocumentosController class.
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
package com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db.desafio.votacao.api.v1.misc.exceptions.NotFoundException;
import com.db.desafio.votacao.api.v1.modules.controllers.Controller;
import com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.controllers.swagger.ValidacaoDocumentosSwagger;
import com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.database.enums.ValidEnum;
import com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.database.models.Valid;
import com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.services.CNPJService;
import com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.services.CPFService;

@RestController
@RequestMapping( path = Controller.VERSION + "valid" )
public class ValidacaoDocumentosController 
    extends 
        Controller
    implements
        ValidacaoDocumentosSwagger

{
    @Autowired
    private CPFService cpfService;
    
    @Autowired
    private CNPJService cnpjService;

    @Override
    @GetMapping("{cpfOrCnpj}")
    public ResponseEntity<Valid> getValid( 
        @PathVariable("cpfOrCnpj") String cpfOrCnpj, 
        @RequestParam( name = "type") String type )
    {
        Valid valid = new Valid();
        valid.setStatus( ValidEnum.UNABLE_TO_VOTE );

        try
        {
            if( type.equalsIgnoreCase("CPF"))
            {
                valid = cpfService.validate( cpfOrCnpj );

                return ok( valid );
            }
            else if( type.equalsIgnoreCase("CNPJ" ))
            {
                valid = cnpjService.validate( cpfOrCnpj );

                return ok( valid );
            }
        }
        catch( NotFoundException notFoundException )
        {
            return new ResponseEntity<Valid>( valid, HttpStatus.NOT_FOUND );
        }

        return ok( valid );
    }
}
