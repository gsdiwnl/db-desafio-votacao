INSERT INTO votos ( id, voto, associado_id, pauta_id ) 
VALUES (
    1,
    0, --Sim
    1,
    1
);

INSERT INTO pauta_votacoes ( pauta_id, votos_id )
VALUES (
    1,
    1
);