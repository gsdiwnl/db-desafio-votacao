DELETE FROM pautas;

INSERT INTO pautas (name, description, start_time, end_time)
VALUES (
    'Pauta originada de TESTES',
    '',
    CURRENT_TIMESTAMP(),
    DATEADD('MINUTE', 1, CURRENT_TIMESTAMP() )
);