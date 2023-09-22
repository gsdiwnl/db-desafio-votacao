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
package db.desafio.votacao.api.v1.models;

import java.time.LocalDateTime;

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
@Table( name = "sessao_votos" )
public class SessaoVoto 
{
    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    private String id;    
    
    @ManyToOne
    @JoinColumn( name = "pauta_id" )
    private Pauta pauta;

    @Column( name = "start_date", nullable = false )
    private LocalDateTime startDate;
    
    @Column( name = "end_date", nullable = false )
    private LocalDateTime endDate;
}
