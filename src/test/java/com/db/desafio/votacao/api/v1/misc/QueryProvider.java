/**
 * Filename:    QueryProvider.java
 *
 * Description: Implementation of the QueryProvider class.
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
package com.db.desafio.votacao.api.v1.misc;

public class QueryProvider 
{
    public static final String resetDB = "/resetDB.sql";
    public static final String insertAssembleia = "/insertAssembleia.sql";
    public static final String insertPauta = "/insertPauta.sql";
    public static final String insertFinishedPauta = "/insertFinishedPauta.sql";
    public static final String insertAssociadoAbleToVote = "/insertAssociadoAbleToVote.sql";
    public static final String insertAssociadoUnableToVote = "/insertAssociadoUnableToVote.sql";
    public static final String insertVotoSim = "/insertVotoSim.sql";
    public static final String insertVotoNao = "/insertVotoNao.sql";
    public static final String insertVotoAbstencao = "/insertVotoAbstencao.sql";
    public static final String insertVotoBranco = "/insertVotoBranco.sql";
}
