/**
 * Filename:    RegisterAssembleiaDTO.java
 *
 * Description: Implementation of the RegisterAssembleiaDTO class.
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.db.desafio.votacao.api.v1.config.ApplicationContext;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterAssembleiaDTO
{
    @NotNull( message = "Necessário informar o nome da Assembleia" )
	private String name;

	@Builder.Default
	private String description = "";

	@Builder.Default
	@JsonIgnore
	private List<Pauta> pautas = new ArrayList<>();

	@Builder.Default
	private LocalDate creationDate = ApplicationContext.today();

	@Builder.Default
	private LocalDate startDate = ApplicationContext.today();
	
    @Builder.Default
	private LocalDate endDate = ApplicationContext.today().plusDays( 1 );
}
