/**
 * Filename:    PautaServiceTest.java
 *
 * Description: Implementation of the PautaServiceTest class.
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
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.db.desafio.votacao.api.v1.misc.exceptions.BadRequestException;
import com.db.desafio.votacao.api.v1.misc.exceptions.NotFoundException;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.PautaRepository;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.PautaResultDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Pauta;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.models.PautaStub;

@DisplayName("Pauta service")
public class PautaServiceTest 
{
    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    private Pauta mockPautaWithId = PautaStub.createPautaWithId();
    private Pauta mockPautaWithWrongDates = PautaStub.createPautaWithWrongDates();
    private PautaResultDTO mockPautaResult = PautaStub.createPautaResult();

    @BeforeEach
    public void initializer()
    {
        openMocks(this);

        when( pautaRepository.save( any( Pauta.class ))).thenReturn( mockPautaWithId );
        when( pautaRepository.findById( 1L )).thenReturn( Optional.of( mockPautaWithId ));
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar Ok ao criar pauta")
    public void Should_ReturnOk_CreatePauta()
    {
        Pauta pauta = pautaService.addPauta( mockPautaWithId );
        
        assertEquals( mockPautaWithId, pauta );
        verify( pautaRepository, times( 1 )).save( any( Pauta.class ));
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar BadRequest ao criar pauta com datas inválidas")
    public void Should_ReturnBadRequest_CreatePauta()
    {
        BadRequestException exception = assertThrows( BadRequestException.class, 
                                                    () -> pautaService.addPauta( mockPautaWithWrongDates ));

        assertEquals( "Pauta (" + mockPautaWithWrongDates.getName() + ") não pode iniciar com data anterior a atual", exception.getMessage() );
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar Ok ao buscar o resultado da pauta")
    public void Should_ReturnOk_GetPautaResult()
    {
        PautaResultDTO pautaResult = pautaService.getPautaResult( 1 );

        assertEquals( mockPautaResult, pautaResult );
        verify( pautaRepository, times( 1 )).findById( any( Long.class ));
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar Ok ao buscar pauta")
    public void Should_ReturnOk_GetPauta()
    {
        Pauta pauta = pautaService.getPautaById( 1L );

        assertEquals( mockPautaWithId, pauta );
        verify( pautaRepository, times( 1 )).findById( any( Long.class ));
    }
    
    @Test
    @DisplayName("[SERVICE] Deve retornar NotFound ao buscar resultado da pauta")
    public void Should_ReturnNotFound_GetPautaResult()
    {
        long pautaId = -1;

        NotFoundException exception = assertThrows( NotFoundException.class, 
                                                    () -> pautaService.getPautaResult( pautaId ));

        assertEquals( "Pauta não encontrada para ID: #" + pautaId , exception.getMessage());
    }
}
