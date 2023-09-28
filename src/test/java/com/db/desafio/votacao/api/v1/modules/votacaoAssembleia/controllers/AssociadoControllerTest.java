/**
 * Filename:    AssociadoControllerTest.java
 *
 * Description: Implementation of the AssociadoControllerTest class.
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
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Associado;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.models.AssociadoStub;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Associado controller")
public class AssociadoControllerTest 
{
    @Autowired
	private MockMvc mockMvc;

	private String PATH = "/v1/associados";

    @Test
	@DisplayName("[POST] Deve retornar Ok ao criar um Associado")
    public void Should_ReturnOk_CreateAssociado() throws Exception
    {
        final Associado mockAssociado = AssociadoStub.createAssociadoWithoutId();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAssociado )))
				.andExpect( status().isCreated() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.name").value( mockAssociado.getName() ))
				.andExpect( jsonPath("$.document").value( mockAssociado.getDocument() ));
    }

    @Test
	@DisplayName("[POST] Deve retornar BadRequest ao criar um Associado com CPF inválido")
    public void Should_ReturnBadRequest_CreateAssociado() throws Exception
    {
        final Associado mockAssociado = AssociadoStub.createAssociadoWithWrongCPF();

        mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAssociado )))
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath("$.code").value( 400 ))
				.andExpect( jsonPath("$.status").value( "Bad Request" ))
				.andExpect( jsonPath("$.message").value( "CPF ou CNPJ inválido" ));
    }
    
    @Test
    	@SqlGroup({
		@Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
		@Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB )
	})
	@DisplayName("[POST] Deve retornar Forbidden ao criar um Associado com documento que já existe")
    public void Should_ReturnForbidden_CreateAssociado() throws Exception
    {
        final Associado mockAssociado = AssociadoStub.createAssociadoWithoutId();

        mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAssociado )))
				.andExpect( status().isForbidden() )
				.andExpect( jsonPath("$.code").value( 403 ))
				.andExpect( jsonPath("$.status").value( "Forbidden" ))
				.andExpect( jsonPath("$.message").value( "Associado já existe para documento informado: 53347243030" ));
    }

    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB )
	})
    @DisplayName("[GET] Deve retornar Ok ao buscar um associado")
    public void Should_ReturnOk_GetAssociado() throws Exception
    {
        final Associado mockAssociado = AssociadoStub.createAssociadoWithId();

		mockMvc.perform( get( PATH + "/{document}", mockAssociado.getDocument() ))
                
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.name").value( mockAssociado.getName() ))
				.andExpect( jsonPath("$.document").value( mockAssociado.getDocument() ))
				.andExpect( jsonPath("$.status").value( mockAssociado.getStatus().getValue() ));
    }
    
    @Test
	@DisplayName("[POST] Deve retornar NotFound ao buscar um Associado que não existe")
    public void Should_ReturnNotFound_GetAssociado() throws Exception
    {
        String associadoDocument = "53347243030";

        mockMvc.perform( get( PATH + "/{document}", associadoDocument ))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Associado não encontrado para documento: " + associadoDocument ));
    }
}
