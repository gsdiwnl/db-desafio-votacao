/**
 * Filename:    PautaSwagger.java
 *
 * Description: Implementation of the PautaSwagger class.
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

import com.db.desafio.votacao.api.v1.misc.Error;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterPautaDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.PautaResultDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface PautaSwagger 
{
    final String TAG_NAME = "Pauta";

    @Operation(
        operationId = "buscar pautas",
        summary = "Busca todas pautas",
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
    public ResponseEntity<List<Pauta>> getPautas();

    @Operation(
        operationId = "cadastra pauta",
        summary = "Cadastra nova pauta",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Operação bem sucedida",
                content = @Content( 
                            schema = @Schema( implementation = Pauta.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Dados enviados inválidos, confira documentação",
                content = @Content( 
                            schema = @Schema( implementation = Error.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<Pauta> createPauta( @RequestBody RegisterPautaDTO pautaDTO );

    @Operation(
        operationId = "busca resultado pauta",
        summary = "Busca os resultados de uma pauta",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Operação bem sucedida",
                content = @Content( 
                            schema = @Schema( implementation = PautaResultDTO.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Dados enviados inválidos, confira documentação",
                content = @Content( 
                            schema = @Schema( implementation = Error.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<PautaResultDTO> getPautaResult( @PathVariable("pautaId") long pautaId );
}
