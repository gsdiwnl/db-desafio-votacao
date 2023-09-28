/**
 * Filename:    AssociadoService.java
 *
 * Description: Implementation of the AssociadoService class.
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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.db.desafio.votacao.api.v1.config.ApplicationContext;
import com.db.desafio.votacao.api.v1.misc.exceptions.NotFoundException;
import com.db.desafio.votacao.api.v1.misc.exceptions.ObjectAlreadyExistsException;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.AssociadoRepository;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterAssociadoDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.AssociadoStatusEnum;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Associado;

@Service
public class AssociadoService 
{
    private final Logger logger = LoggerFactory.getLogger( PautaService.class );

    @Autowired
    private AssociadoRepository associadoRepository;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * getAssociados
     * 
     * @return List<Associado>
     */
    public List<Associado> getAssociados()
    {
        logger.info( "Método: Buscar todos associados");
        
        return associadoRepository.findAll();
    }
    
    /**
     * getAssociadoByDocumento
     * 
     * @param documento String
     * @return Associado
     */
    public Associado getAssociadoByDocument( String document )
    {
        logger.info("Método: Buscar associado pelo documento");

        return associadoRepository.findByDocument( document )
                                    .orElseThrow( () -> new NotFoundException( "Associado não encontrado para documento: " + document ));
    }

    /**
     * createAssociado
     * 
     * @param dto RegisterAssociadoDTO
     * @return Associado
     */
    public Associado createAssociado( RegisterAssociadoDTO dto )
    {
        logger.info("Método: Criar novo associado");

        Associado associado = Associado.builder()
                                        .name( dto.getName() )
                                        .document( dto.getDocument() )
                                        .build();

        return addAssociado( associado );
    }
    
    /**
     * createAssociado
     * 
     * @param associado Associado
     * @return Associado
     */
    public Associado addAssociado( Associado associado )
    {
        logger.info("Método: Salvar novo associado");

        if( hasAssociadoForDocument( associado.getDocument() ))
        {
            throw new ObjectAlreadyExistsException( "Associado já existe para documento informado: " + associado.getDocument() );
        }

        AssociadoStatusEnum status = validateDocument( associado.getDocument() ) ? AssociadoStatusEnum.ABLE_TO_VOTE : AssociadoStatusEnum.UNABLE_TO_VOTE;
        associado.setStatus( status );

        return associadoRepository.save( associado );
    }

    /**
     * hasAssociadoForDocument
     * 
     * @param document String
     * @return boolean
     */
    public boolean hasAssociadoForDocument( String document )
    {
        return associadoRepository.existsByDocument( document );
    }

    /**
     * validateDocumento
     * 
     * @param documento String
     * @return boolean
     */
    public boolean validateDocument( String document )
    {
        logger.info("Método: Validar documento de novo associado");

        String type = document.length() == 11 ? "CPF" : "CNPJ";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl( ApplicationContext.getServerBaseUrl() )
                                                                .path( "/valid/{cpfOrCnpj}" )
                                                                .queryParam("type", type );

        String apiUrl = uriBuilder.buildAndExpand( document ).toUriString();

        try
        {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity( apiUrl, String.class);
    
            if( responseEntity.getStatusCode().is2xxSuccessful() )
            {
                return true;
            }
        }
        catch( RestClientException ex )
        {
            // ignore exception
        }

        return false;
    }
}
