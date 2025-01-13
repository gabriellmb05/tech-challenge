-- CREATE TABLE CLIENTE
CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(256) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(100) NOT NULL,
    data_nascimento DATE
);

-- INSERTS
INSERT INTO clientes (nome, cpf, email, data_nascimento)
VALUES ('Elmo Cameron', '43316652616', 'lectus@yahoo.edu', '2025-03-28');

INSERT INTO clientes (nome, cpf, email, data_nascimento)
VALUES ('Phelan Vang', '47374738523', 'ut.molestie@google.edu', '2024-07-14');