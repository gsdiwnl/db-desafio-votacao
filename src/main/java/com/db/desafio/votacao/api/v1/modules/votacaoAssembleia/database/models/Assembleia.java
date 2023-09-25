/**
 * Filename:    SessaoVoto.java
 *
 * Description: Implementation of the SessaoVoto class.
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "assembleias" )
public class Assembleia 
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;  
    
    private String name;
    private String description;
    private LocalDate creationDate;

    @OneToMany( cascade = CascadeType.ALL )
    @JoinTable(
        name = "assembleia_pautas", 
        joinColumns = {
            @JoinColumn(
                name = "assembleia_id", 
                referencedColumnName = "id"
            )
        },
        inverseJoinColumns = { 
            @JoinColumn(
                name = "pauta_id",
                referencedColumnName = "id"
            )
        }
    )
    List<Pauta> pautas = new ArrayList<>();

    @Column( name = "start_date", nullable = false )
    private LocalDate startDate;
    
    @Column( name = "end_date", nullable = false )
    private LocalDate endDate;
}
