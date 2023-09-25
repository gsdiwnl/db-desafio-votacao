/**
 * Filename:    VotoRepository.java
 *
 * Description: Implementation of the VotoRepository class.
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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Voto;

@Repository
public interface VotoRepository 
    extends
        JpaRepository<Voto, Long>
{
	boolean existsByAssociadoIdAndPautaId( Long associadoId, Long pautaId );
}
