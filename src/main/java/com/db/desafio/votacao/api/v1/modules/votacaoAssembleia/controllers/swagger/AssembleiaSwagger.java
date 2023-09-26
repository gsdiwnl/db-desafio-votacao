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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.db.desafio.votacao.api.v1.config.SwaggerConfig;
import com.db.desafio.votacao.api.v1.misc.Error;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterAssembleiaDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Assembleia;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

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
                description = SwaggerConfig.SUCCESS_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Assembleia.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<List<Assembleia>> getAssembleias();

    @Operation(
        operationId = "buscar assembleia",
        summary = "Busca assembleia pelo ID informado",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = SwaggerConfig.SUCCESS_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Assembleia.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            ),
            @ApiResponse(
                responseCode = "404",
                description = SwaggerConfig.NOT_FOUND_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Error.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<Assembleia> getAssembleia( @PathVariable("assembleiaId") long assembleiaId );

    @Operation(
        operationId = "criar assembleia",
        summary = "Criar nova assembleia",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = SwaggerConfig.SUCCESS_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Assembleia.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            ),
            @ApiResponse(
                responseCode = "400",
                description = SwaggerConfig.BAD_REQUEST_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Error.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<Assembleia> createAssembleia( @RequestBody @Valid RegisterAssembleiaDTO assembleiaDTO );
}
