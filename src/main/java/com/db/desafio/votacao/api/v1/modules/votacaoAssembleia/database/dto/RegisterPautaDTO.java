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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.PautaStatusEnum;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Voto;
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
public class RegisterPautaDTO
{
	@NotNull( message = "Necessário informar o ID da Assembleia" )
	private long assembleiaId;

	@NotNull( message = "Necessário informar o nome da Pauta" )
	private String name;

	@Builder.Default
	private String description = "Sem descrição";

	@Builder.Default
	@JsonIgnore
	private List<Voto> votos = new ArrayList<>();

	@Builder.Default
	private LocalDateTime startTime = LocalDateTime.now();

	@Builder.Default
	private LocalDateTime endTime = LocalDateTime.now().plusMinutes( 1 );

	@Builder.Default
	@JsonIgnore
	private PautaStatusEnum status = PautaStatusEnum.AGUARDANDO_VOTACAO;

}