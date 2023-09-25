package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Associado;

@Repository
public interface AssociadoRepository
    extends
        JpaRepository<Associado, Long> 
{
    Optional<Associado>findByDocument( String document );
}
