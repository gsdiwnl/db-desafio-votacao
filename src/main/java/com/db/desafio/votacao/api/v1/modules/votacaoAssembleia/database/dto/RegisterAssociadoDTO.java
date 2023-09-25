/**
 * Filename:    RegisterAssociadoDTO.java
 *
 * Description: Implementation of the RegisterAssociadoDTO class.
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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto;

import com.db.desafio.votacao.api.v1.misc.annotations.CpfOrCnpj;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterAssociadoDTO 
{
    @NotNull( message = "Necessário informar o nome do(a) Associado(a)" )
	private String name;
    
    @NotNull( message = "Necessário informar o documento (CPF ou CNPJ) do(a) Associado(a)" )
    @CpfOrCnpj()
    private String document;
}
