package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.desafio.votacao.api.v1.modules.controllers.Controller;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.controllers.swagger.AssociadoSwagger;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto.RegisterAssociadoDTO;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Associado;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.services.AssociadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( path = Controller.VERSION + "associados" )
public class AssociadoController 
    extends 
        Controller
    implements
        AssociadoSwagger
{

    @Autowired
    private AssociadoService associadoService;

    /**
     * getAssociados
     * 
     * @return ResponseEntity<List<Associado>>
     */
    @Override
    @GetMapping()
    public ResponseEntity<List<Associado>> getAssociados() 
    {
        return ok( associadoService.getAssociados() );
    }

    /**
     * createAssociado
     * 
     * @param associadoDTO RegisterAssociadoDTO
     * @return ResponseEntity<Associado>
     */
    @Override
    @PostMapping()
    public ResponseEntity<Associado> createAssociado( @RequestBody @Valid RegisterAssociadoDTO associadoDTO ) 
    {
        Associado associado = new Associado();

        associado.setName( associadoDTO.getName() );
        associado.setDocument( associadoDTO.getDocument() );

        return created( associadoService.addAssociado( associado ));
    }
}
