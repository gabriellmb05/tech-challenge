-- CREATE TABLE CLIENTE
CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(256) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(100) NOT NULL,
    data_nascimento DATE
);

-- CREATE SEQUENCE
CREATE SEQUENCE CLIENTES_ID_SEQ START WITH 1 INCREMENT BY 1;

-- INSERTS
INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (NEXTVAL('CLIENTES_ID_SEQ'), 'Elmo Cameron', '43316652616', 'lectus@yahoo.edu', '2025-03-28');

INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (NEXTVAL('CLIENTES_ID_SEQ'), 'Phelan Vang', '47374738523', 'ut.molestie@google.edu', '2024-07-14');