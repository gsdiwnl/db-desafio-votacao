/**
 * Filename:    AssembleiaServiceTest.java
 *
 * Description: Implementation of the AssembleiaServiceTest class.
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
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.AssembleiaRepository;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Assembleia;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.models.AssembleiaStub;

@DisplayName("Assembleia service")
public class AssembleiaServiceTest 
{
    @InjectMocks
    private AssembleiaService assembleiaService;

    @Mock
    private AssembleiaRepository assembleiaRepository;

    private Assembleia mockAssembleiaWithId = AssembleiaStub.createAssembleiaWithId();
    private Assembleia mockAssembleiaWithoutId = AssembleiaStub.createAssembleiaWithId();
    private Assembleia mockAssembleiaWithWrongDates = AssembleiaStub.createAssembleiaWithWrongDates();
    private Assembleia mockAssembleiaWithEndDateBefore = AssembleiaStub.createAssembleiaWithEndDateBefore();

    @BeforeEach
    public void initializer()
    {
        openMocks(this);

        when( assembleiaRepository.save( any( Assembleia.class ))).thenReturn( mockAssembleiaWithId );
        when( assembleiaRepository.findById( 1L )).thenReturn( Optional.of( mockAssembleiaWithId ));
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar Ok ao criar assembleia")
    public void Should_ReturnOk_CreateAssembleia()
    {
        Assembleia assembleia = assembleiaService.addAssembleia( mockAssembleiaWithoutId );
        
        assertEquals( mockAssembleiaWithId, assembleia );
        verify( assembleiaRepository, times( 1 )).save( any( Assembleia.class ));
    }
    
    @Test
    @DisplayName("[SERVICE] Deve retornar BadRequest ao criar assembleia com datas inválidas")
    public void Should_ReturnBadRequest_CreateAssembleia()
    {
        BadRequestException exception = assertThrows( BadRequestException.class, 
                                                    () -> assembleiaService.addAssembleia( mockAssembleiaWithWrongDates ));

        assertEquals( "Assembleia (" + mockAssembleiaWithWrongDates.getName() + ") não pode iniciar com data anterior ao dia atual", exception.getMessage() );
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar Ok ao buscar assembleia pelo ID informado")
    public void Should_ReturnOk_GetAssembleiaById()
    {
        Assembleia assembleia = assembleiaService.getAssembleiaById( 1L );

        assertEquals( mockAssembleiaWithId, assembleia );
        verify( assembleiaRepository, times( 1 )).findById( any( Long.class ));
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar BadRequest ao validar assembleia com data final anterior a inicial")
    public void Should_ReturnBadRequest_ValidDates()
    {
        BadRequestException exception = assertThrows( BadRequestException.class, 
                                                    () -> assembleiaService.validDates( mockAssembleiaWithEndDateBefore ));

        assertEquals( "Data inicial da assembleia deve ser anterior à data final", exception.getMessage() );
    }
}
