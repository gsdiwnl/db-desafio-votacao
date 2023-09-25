/**
 * Filename:    Valid.java
 *
 * Description: Implementation of the Valid class.
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
package com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.database.models;

import com.db.desafio.votacao.api.v1.modules.validacaoDocumentos.database.enums.ValidEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Valid
{
    @Builder.Default
    ValidEnum status = ValidEnum.UNABLE_TO_VOTE;
}
