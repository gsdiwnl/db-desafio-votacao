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
        final RegisterPautaDTO mockPautaDTO = PautaStub.createPautaWithoutId();
		
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


}
