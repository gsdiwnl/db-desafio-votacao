/**
 * Filename:    ValidacaoDocumentosSwagger.java
 *
 * Description: Implementation of the ValidacaoDocumentosSwagger class.
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
package com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.controllers.swagger;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.database.models.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface ValidacaoDocumentosSwagger 
{
    final String TAG_NAME = "Validação de documentos";

    @Operation(
        operationId = "validar documentos",
        summary = "Valida se o CPF ou CNPJ do Associado é valido",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "CPF ou CNPJ válido",
                content = @Content( 
                            schema = @Schema( implementation = Valid.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "CPF ou CNPJ inválido",
                content = @Content( 
                            schema = @Schema( implementation = Valid.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<Valid> getValid(
        @PathVariable("cpf") String cpfOrCnpj,
        @RequestParam( name = "type") String type );
}
