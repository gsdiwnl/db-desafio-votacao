DELETE FROM pautas;

INSERT INTO pautas ( id, name, description, start_time, end_time )
VALUES (
    1,
    'Pauta originada de TESTES',
    '',
    DATEADD( 'DAY', -1, CURRENT_TIMESTAMP() ),
    DATEADD( 'DAY', -1, DATEADD('MINUTE', 1, CURRENT_TIMESTAMP() ))
);