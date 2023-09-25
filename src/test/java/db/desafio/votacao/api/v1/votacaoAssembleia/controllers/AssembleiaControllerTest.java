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
package db.desafio.votacao.api.v1.votacaoAssembleia.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.controllers.AssembleiaController;

import db.desafio.votacao.api.v1.votacaoAssembleia.models.AssembleiaStub;

@WebMvcTest( AssembleiaController.class )
public class AssembleiaControllerTest 
{
    @Autowired
    private MockMvc mockMvc;

    @Test
	public void shouldReturnDefaultMessage() throws Exception 
    {
		mockMvc.perform( post("/v1/assembleia")
                            .contentType( MediaType.APPLICATION_JSON )
                            .content( AssembleiaStub.createAssembleiaJson() ))
                .andExpect( status().isCreated() );
	}
}
