DELETE FROM pautas;

INSERT INTO pautas ( id, name, description, start_time, end_time )
VALUES (
    1,
    'Pauta originada de TESTES',
    '',
    CURRENT_TIMESTAMP(),
    DATEADD('MINUTE', 1, CURRENT_TIMESTAMP() )
);