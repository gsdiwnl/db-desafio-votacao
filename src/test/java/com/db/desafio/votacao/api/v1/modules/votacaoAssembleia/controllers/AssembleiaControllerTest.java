/**
 * Filename:    AssembleiaControllerTest.java
 *
 * Description: Implementation of the AssembleiaControllerTest class.
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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.controllers;

import static com.db.desafio.votacao.api.v1.misc.Serializer.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import com.db.desafio.votacao.api.v1.config.ApplicationContext;
import com.db.desafio.votacao.api.v1.misc.QueryProvider;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Assembleia;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.models.AssembleiaStub;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Assembleia controller")
class AssembleiaControllerTest 
{
	@Autowired
	private MockMvc mockMvc;

	private String PATH = "/v1/assembleias";

	@Test
	@DisplayName("[POST] Deve retornar Ok ao criar uma Assembleia")
	public void Should_ReturnCreated_CreateAssembleia() throws Exception 
	{
		final Assembleia mockAssembleia = AssembleiaStub.createAssembleiaWithoutId();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAssembleia )))
				.andExpect( status().isCreated() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.startDate").value( mockAssembleia.getStartDate().toString() ))
				.andExpect( jsonPath("$.endDate").value( mockAssembleia.getEndDate().toString() ))
				.andExpect( jsonPath("$.description").value( mockAssembleia.getDescription() ))
				.andExpect( jsonPath("$.creationDate").value( ApplicationContext.today().toString() ));
	}

	@Test
	@DisplayName("[POST] Deve retornar BadRequest ao criar uma Assembleia com datas inválidas")
	public void Should_ReturnBadRequest_CreateAssembleia() throws Exception
	{
		final Assembleia mockAssembleia = AssembleiaStub.createAssembleiaWithWrongDates();

		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAssembleia )))
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath("$.code").value( 400 ))
				.andExpect( jsonPath("$.status").value( "Bad Request" ))
				.andExpect( jsonPath("$.message").value( "Assembleia (" + mockAssembleia.getName() + ") não pode iniciar com data anterior ao dia atual" ));
	}

	@Test
	@SqlGroup({
		@Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssembleia ),
		@Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB )
	})
	@DisplayName("[GET] Deve retornar Ok ao buscar uma Assembleia pelo ID informado")
	public void Should_ReturnOk_GetAssembleiaById() throws Exception
	{
		final Assembleia mockAssembleia = AssembleiaStub.createAssembleiaWithoutId();
		
		mockMvc.perform( get( PATH + "/{assembleiaId}", 1 ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.startDate").value( mockAssembleia.getStartDate().toString() ))
				.andExpect( jsonPath("$.endDate").value( mockAssembleia.getEndDate().toString() ))
				.andExpect( jsonPath("$.description").value( mockAssembleia.getDescription() ))
				.andExpect( jsonPath("$.creationDate").value( ApplicationContext.today().toString() ));
	}

	@Test
	@DisplayName("[GET] Deve retornar NotFound ao buscar uma Assembleia pelo ID inválido")
	public void Should_ReturnNotFoundException_GetAssembleiaById() throws Exception
	{
		long assembleiaId = -1;

		mockMvc.perform( get( PATH + "/{assembleiaId}", assembleiaId ))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Assembleia não encontrada para ID: #" + assembleiaId ));
	}
}