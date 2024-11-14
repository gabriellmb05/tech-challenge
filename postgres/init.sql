CREATE TABLE clientes (
  id SERIAL PRIMARY KEY,
  cpf VARCHAR(11),
  email VARCHAR(50)
);

INSERT INTO clientes (cpf, email) VALUES
  ('11111111111', 'gabriel.bianchi@gmail.com');