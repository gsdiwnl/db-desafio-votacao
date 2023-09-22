/**
 * Filename:    AssembleiaSwagger.java
 *
 * Description: Implementation of the AssembleiaSwagger class.
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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.controllers.swagger;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Assembleia;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface AssembleiaSwagger 
{
    final String TAG_NAME = "Assembleia";

    @Operation(
        operationId = "buscar assembleias",
        summary = "Busca todas assembleias",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Operação bem sucedida",
                content = @Content( 
                            schema = @Schema( implementation = Pauta.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<List<Assembleia>> getAssembleias();
}
