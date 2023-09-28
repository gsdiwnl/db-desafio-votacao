/**
 * Filename:    AssociadoServiceTest.java
 *
 * Description: Implementation of the AssociadoServiceTest class.
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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.AssociadoRepository;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Associado;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.models.AssociadoStub;

@DisplayName("Associado service")
public class AssociadoServiceTest 
{
    @InjectMocks
    private AssociadoService associadoService;

    @Mock
    private AssociadoRepository associadoRepository;

    private Associado mockAssociadoWithId = AssociadoStub.createAssociadoWithId();

    @BeforeEach
    public void initializer()
    {
        openMocks(this);

        when( associadoRepository.save( any( Associado.class ))).thenReturn( mockAssociadoWithId );
        when( associadoRepository.findByDocument( "53347243030" )).thenReturn( Optional.of( mockAssociadoWithId ));
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar Ok ao buscar Associado por documento")
    public void Should_ReturnOk_GetAssociado()
    {
        Associado associado = associadoService.getAssociadoByDocument( "53347243030" );

        assertEquals( mockAssociadoWithId, associado );
        verify( associadoRepository, times( 1 )).findByDocument( any( String.class ));
    }
}
