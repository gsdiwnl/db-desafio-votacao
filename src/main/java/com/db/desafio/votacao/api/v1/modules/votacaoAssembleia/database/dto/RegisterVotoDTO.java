/**
 * Filename:    RegisterVotoDTO.java
 *
 * Description: Implementation of the RegisterVotoDTO class.
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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.dto;

import com.db.desafio.votacao.api.v1.misc.annotations.CpfOrCnpj;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.VotoEnum;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterVotoDTO 
{
    @NotNull( message = "Necessário informar o documento do(a) Associado(a)" )
    @CpfOrCnpj()
    String documentAssociado;
    
    @NotNull( message = "Necessário informar o ID da Pauta" )
    Long pautaId;
    
    @NotNull( message = "Necessário informar o voto ('Sim', 'Não', 'Abstenção', 'Branco')" )
    VotoEnum voto;
}
