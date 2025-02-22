/**
 * Filename:    PautaControllerTest.java
 *
 * Description: Implementation of the PautaControllerTest class.
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

import com.db.desafio.votacao.api.v1.misc.QueryProvider;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterPautaDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.PautaStatusEnum;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.models.PautaStub;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Pauta controller")
public class PautaControllerTest 
{
    @Autowired
	private MockMvc mockMvc;

	private String PATH = "/v1/pautas";

    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssembleia ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[POST] Deve retornar Ok ao criar uma Pauta")
    public void Should_ReturnOk_CreatePauta() throws Exception
    {
        final RegisterPautaDTO mockPautaDTO = PautaStub.createPautaDTOWithoutId();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockPautaDTO )))
				.andExpect( status().isCreated() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.name").value( mockPautaDTO.getName() ))
				.andExpect( jsonPath("$.description").value( mockPautaDTO.getDescription() ))
				.andExpect( jsonPath("$.votos").value( mockPautaDTO.getVotos() ))
				.andExpect( jsonPath("$.startTime").value( mockPautaDTO.getStartTime().toString() ))
				.andExpect( jsonPath("$.endTime").value( mockPautaDTO.getEndTime().toString() ))
				.andExpect( jsonPath("$.status").value( PautaStatusEnum.AGUARDANDO_VOTACAO.getValue() ));
    }

	@Test
	@Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.resetDB )
	@DisplayName("[POST] Deve retornar NotFound ao criar uma Pauta e não existir Assembleia")
	public void Should_ReturnNotFound_CreatePauta() throws Exception
	{
		final RegisterPautaDTO mockPautaDTO = PautaStub.createPautaDTOWithoutId();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockPautaDTO )))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Assembleia não encontrada para ID: #" + mockPautaDTO.getAssembleiaId() ));
	}
	
	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssembleia ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[POST] Deve retornar BadRequest ao criar uma Pauta com datas inválidas")
	public void Should_ReturnBadRequest_CreatePautaWithWrongDates() throws Exception
	{
		final RegisterPautaDTO mockPautaDTO = PautaStub.createPautaDTOWithWrongDates();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockPautaDTO )))
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath("$.code").value( 400 ))
				.andExpect( jsonPath("$.status").value( "Bad Request" ))
				.andExpect( jsonPath("$.message").value( "Data inicial e final da Pauta devem estar dentro do escopo de datas da Assembleia" ));
	}

	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertPauta ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[GET] Deve retornar Ok ao buscar o resultado da Pauta")
	public void Should_ReturnOk_GetPautaResult() throws Exception
	{
		long pautaId = 1;

		mockMvc.perform( get( PATH + "/{pautaId}", pautaId ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.pautaId").value( pautaId ))
				.andExpect( jsonPath("$.description").value( "" ))
				.andExpect( jsonPath("$.approved").value( 0 ))
				.andExpect( jsonPath("$.rejected").value( 0 ))
				.andExpect( jsonPath("$.abstention").value( 0 ))
				.andExpect( jsonPath("$.protest").value( 0 ))
				.andExpect( jsonPath("$.total").value( 0 ))
				.andExpect( jsonPath("$.status").value( PautaStatusEnum.AGUARDANDO_VOTACAO.getValue() ));
	}

	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertFinishedPauta ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertVotoSim ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[GET] Deve retornar Ok ao buscar o resultado da Pauta aprovada")
	public void Should_ReturnOk_GetPautaResult_Approved() throws Exception
	{
		long pautaId = 1;

		mockMvc.perform( get( PATH + "/{pautaId}", pautaId ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.pautaId").value( pautaId ))
				.andExpect( jsonPath("$.description").value( "" ))
				.andExpect( jsonPath("$.approved").value( 1 ))
				.andExpect( jsonPath("$.rejected").value( 0 ))
				.andExpect( jsonPath("$.abstention").value( 0 ))
				.andExpect( jsonPath("$.protest").value( 0 ))
				.andExpect( jsonPath("$.total").value( 1 ))
				.andExpect( jsonPath("$.status").value( PautaStatusEnum.APROVADA.getValue() ));
	}
	
	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertFinishedPauta ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertVotoNao ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[GET] Deve retornar Ok ao buscar o resultado da Pauta reprovada")
	public void Should_ReturnOk_GetPautaResult_Rejected() throws Exception
	{
		long pautaId = 1;

		mockMvc.perform( get( PATH + "/{pautaId}", pautaId ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.pautaId").value( pautaId ))
				.andExpect( jsonPath("$.description").value( "" ))
				.andExpect( jsonPath("$.approved").value( 0 ))
				.andExpect( jsonPath("$.rejected").value( 1 ))
				.andExpect( jsonPath("$.abstention").value( 0 ))
				.andExpect( jsonPath("$.protest").value( 0 ))
				.andExpect( jsonPath("$.total").value( 1 ))
				.andExpect( jsonPath("$.status").value( PautaStatusEnum.REPROVADA.getValue() ));
	}
	
	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertFinishedPauta ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[GET] Deve retornar Ok ao buscar o resultado da Pauta anulada")
	public void Should_ReturnOk_GetPautaResult_Anulada() throws Exception
	{
		long pautaId = 1;

		mockMvc.perform( get( PATH + "/{pautaId}", pautaId ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.pautaId").value( pautaId ))
				.andExpect( jsonPath("$.description").value( "" ))
				.andExpect( jsonPath("$.approved").value( 0 ))
				.andExpect( jsonPath("$.rejected").value( 0 ))
				.andExpect( jsonPath("$.abstention").value( 0 ))
				.andExpect( jsonPath("$.protest").value( 0 ))
				.andExpect( jsonPath("$.total").value( 0 ))
				.andExpect( jsonPath("$.status").value( PautaStatusEnum.ANULADA.getValue() ));
	}
	
	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertFinishedPauta ),
		@Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertVotoSim ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertVotoNao ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[GET] Deve retornar Ok ao buscar o resultado da Pauta empatada")
	public void Should_ReturnOk_GetPautaResult_Tied() throws Exception
	{
		long pautaId = 1;

		mockMvc.perform( get( PATH + "/{pautaId}", pautaId ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.pautaId").value( pautaId ))
				.andExpect( jsonPath("$.description").value( "" ))
				.andExpect( jsonPath("$.approved").value( 1 ))
				.andExpect( jsonPath("$.rejected").value( 1 ))
				.andExpect( jsonPath("$.abstention").value( 0 ))
				.andExpect( jsonPath("$.protest").value( 0 ))
				.andExpect( jsonPath("$.total").value( 2 ))
				.andExpect( jsonPath("$.status").value( PautaStatusEnum.EMPATADA.getValue() ));
	}

	@Test
	@DisplayName("[POST] Deve retornar NotFound ao buscar o resultado da Pauta que não existe")
	public void Should_ReturnNotFound_GetPautaResult() throws Exception
	{
		long pautaId = -1;

		mockMvc.perform( get( PATH + "/{pautaId}", pautaId ))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Pauta não encontrada para ID: #" + pautaId ));
	}
}
