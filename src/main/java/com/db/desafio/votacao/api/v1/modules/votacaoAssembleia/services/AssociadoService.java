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
import com.db.desafio.votacao.api.v1.misc.exceptions.ObjectAlreadyExistsException;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.AssociadoRepository;
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

        return associadoRepository.findByDocument( document ).orElse( null );
    }
    
    /**
     * createAssociado
     * 
     * @param associado Associado
     * @return Associado
     */
    public Associado addAssociado( Associado associado )
    {
        logger.info("Método: Criar novo associado");

        if( getAssociadoByDocument( associado.getDocument() ) != null )
            throw new ObjectAlreadyExistsException( "Associado já existe para documento informado: " + associado.getDocument() );

        AssociadoStatusEnum status = validateDocument( associado.getDocument() ) ? AssociadoStatusEnum.ABLE_TO_VOTE : AssociadoStatusEnum.UNABLE_TO_VOTE;
        associado.setStatus( status );

        return associadoRepository.save( associado );
    }

    /**
     * validateDocumento
     * 
     * @param documento String
     * @return boolean
     */
    public boolean validateDocument( String document )
    {
        logger.info("Método: Validar documento de associado");

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
