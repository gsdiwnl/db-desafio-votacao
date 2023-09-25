/**
 * Filename:    Voto.java
 *
 * Description: Implementation of the Voto class.
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

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.VotoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "votos" )
public class Voto 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn( name = "associado_id" )
    private Associado associado;

    @JsonIgnore
    @ManyToOne
    @JoinColumn( name = "pauta_id" )
    private Pauta pauta;

    @Column( name = "voto", nullable = false )
    private VotoEnum voto;
}
