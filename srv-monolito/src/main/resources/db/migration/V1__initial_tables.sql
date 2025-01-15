-- CREATE TABLE CLIENTE
CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(256) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(100) NOT NULL,
    data_nascimento DATE
);

-- CREATE TABLE PRODUTOS
CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(256) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    preco NUMERIC(10, 2) NOT NULL,
    ingredientes TEXT
);
