/**
 * Filename:    AssociadoSwagger.java
 *
 * Description: Implementation of the AssociadoSwagger class.
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
import org.springframework.web.bind.annotation.RequestBody;

import com.db.desafio.votacao.api.v1.misc.Error;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterAssociadoDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Associado;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface AssociadoSwagger 
{
    final String TAG_NAME = "Associado";

    @Operation(
        operationId = "buscar associados",
        summary = "Busca todas associados",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Operação bem sucedida",
                content = @Content( 
                            schema = @Schema( implementation = Associado.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<List<Associado>> getAssociados();
    
    @Operation(
        operationId = "criar associado",
        summary = "Cria um novo associado",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Operação bem sucedida",
                content = @Content( 
                            schema = @Schema( implementation = Associado.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Dados enviados inválidos ou associado já existe",
                content = @Content( 
                            schema = @Schema( implementation = Error.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<Associado> createAssociado( @RequestBody @Valid RegisterAssociadoDTO associadoDTO );
}
