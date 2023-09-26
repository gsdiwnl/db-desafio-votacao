DELETE FROM assembleias;

INSERT INTO assembleias ( id, name, description, creation_date, start_date, end_date ) 
VALUES (
    1, 
    'Assembleia originada de TESTES', 
    'Sem descrição', 
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    DATEADD(DAY, 1, CURRENT_TIMESTAMP)
);