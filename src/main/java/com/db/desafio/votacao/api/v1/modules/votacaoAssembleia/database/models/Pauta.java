/**
 * Filename:    Pauta.java
 *
 * Description: Implementation of the Pauta class.
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

import java.time.LocalDateTime;
import java.util.List;

import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.PautaStatusEnum;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.enums.VotoEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "pautas" )
public class Pauta 
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Column( name = "name", nullable = false )
    private String name;

    @Column( name = "description", nullable = true )
    private String description;

    @OneToMany( cascade = CascadeType.ALL )
	@JoinTable(
        name = "pauta_votacoes", 
        joinColumns = {
		    @JoinColumn(
                name = "pauta_id",
                referencedColumnName = "id"
            )
        },
        inverseJoinColumns = {
		    @JoinColumn(
                name = "votos_id",
                referencedColumnName = "id"
            )
        }
    )
	private List<Voto> votos;

    private LocalDateTime startTime;
	private LocalDateTime endTime;

    @Transient
    @Enumerated( EnumType.STRING )
    private PautaStatusEnum status;

    /**
     * getStatus
     * 
     * @return PautaStatusEnum
     */
    public PautaStatusEnum getStatus()
    {
        updateStatus();

        return this.status;
    }

    /**
     * updateStatus
     */
    public void updateStatus()
    {
        if( endTime.isAfter( LocalDateTime.now() ))
        {
            this.status = PautaStatusEnum.AGUARDANDO_VOTACAO;
        }
        else 
        {
            long approvedVotes = votos.stream().filter( voto -> voto.getVoto().equals( VotoEnum.SIM )).count();
            long reprovedVotes = votos.stream().filter( voto -> voto.getVoto().equals( VotoEnum.NAO )).count();

            if( approvedVotes == 0 && reprovedVotes == 0 )
            {
                this.status = PautaStatusEnum.ANULADA;
            }
            else if( approvedVotes == reprovedVotes ) 
            {
                this.status = PautaStatusEnum.EMPATADA;
    
            } 
            else if( approvedVotes > reprovedVotes ) 
            {
                this.status = PautaStatusEnum.APROVADA;
            }
            else 
            {
                this.status = PautaStatusEnum.REPROVADA;
            }
        }
    }
}
