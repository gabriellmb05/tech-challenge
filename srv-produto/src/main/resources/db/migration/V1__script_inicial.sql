CREATE TABLE produtos (
  id SERIAL PRIMARY KEY,
  nome TEXT NOT NULL,
  categoria TEXT NOT NULL,
  preco varchar(100) NOT NULL,
  ingredientes TEXT NULL
);