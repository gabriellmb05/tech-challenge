-- CREATE TABLE produtos
CREATE TABLE produtos (
	id SERIAL PRIMARY KEY,
	nome TEXT NOT NULL,
	categoria TEXT NOT NULL,
	preco NUMERIC NOT NULL,
	ingredientes TEXT NULL
);



-- CREATE SEQUENCE
CREATE SEQUENCE PRODUTOS_ID_SEQ START WITH 1 INCREMENT BY 1;

-- INSERTS
INSERT INTO produtos (id, nome, categoria, preco, ingredientes)
VALUES (NEXTVAL('PRODUTOS_ID_SEQ'), 'x-burguer', 'LANCHE', 10.0, 'Beef, Cheese, Lettuce, Tomato, Bun');

INSERT INTO produtos (id, nome, categoria, preco, ingredientes)
VALUES (NEXTVAL('PRODUTOS_ID_SEQ'), 'x-tudo', 'LANCHE', 10.0, 'Beef, Cheese, Lettuce, Tomato, Bun, egg, bacon');

INSERT INTO produtos (id, nome, categoria, preco, ingredientes)
VALUES (NEXTVAL('PRODUTOS_ID_SEQ'), 'coca-cola lata', 'BEBIDA', 8.0, 'Beef, Cheese, Lettuce, Tomato, Bun, egg, bacon');
