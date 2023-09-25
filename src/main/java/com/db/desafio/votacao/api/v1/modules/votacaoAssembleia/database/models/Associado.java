/**
 * Filename:    Associado.java
 *
 * Description: Implementation of the Associado class.
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
package com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models;

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.AssociadoStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "associados" )
public class Associado 
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;
    
    @Column( name = "name", nullable = false )
    private String name;

    @Column( name = "document", nullable = false )
    private String document;

    @Enumerated( EnumType.STRING )
    private AssociadoStatusEnum status;
}
