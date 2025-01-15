-- INSERT CLIENTES
INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (nextval('clientes_id_seq'), 'Elmo Cameron', '43316652616', 'lectus@yahoo.edu', '2025-03-28');

INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (nextval('clientes_id_seq'), 'Phelan Vang', '47374738523', 'ut.molestie@google.edu', '2024-07-14');

INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (nextval('clientes_id_seq'), 'Lacey Britt', '49473476486', 'ullamcorper@icloud.com', '2025-05-11');

INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (nextval('clientes_id_seq'), 'Moses Gaines', '53625367424', 'luctus@icloud.couk', '2024-05-13');

INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (nextval('clientes_id_seq'), 'Samantha Foreman', '95415747165', 'viverra@hotmail.ca', '2025-06-25');

INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (nextval('clientes_id_seq'), 'Alyssa Sutton', '84876785674', 'amet.consectetuer@yahoo.org', '2025-06-16');

INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (nextval('clientes_id_seq'), 'Kevyn Hill', '78879276162', 'pharetra.nam@hotmail.edu', '2025-08-02');

INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (nextval('clientes_id_seq'), 'Wang May', '88448253614', 'magna.et@icloud.couk', '2024-08-19');

INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (nextval('clientes_id_seq'), 'Hilda Shaffer', '47283628827', 'et.commodo@aol.ca', '2024-12-15');

INSERT INTO clientes (id, nome, cpf, email, data_nascimento)
VALUES (nextval('clientes_id_seq'), 'Len Frank', '51867943259', 'eu.enim.etiam@protonmail.net', '2024-08-29');

-- INSERT PRODUTOS
INSERT INTO produtos (id, nome, categoria, preco, ingredientes)
VALUES (nextval('produtos_id_seq'), 'Pizza Margherita', 'LANCHE', 25.00, 'Tomato, Mozzarella, Basil');

INSERT INTO produtos (id, nome, categoria, preco, ingredientes)
VALUES (nextval('produtos_id_seq'), 'Cheeseburger', 'LANCHE', 15.00, 'Beef, Cheese, Lettuce, Tomato, Bun');

INSERT INTO produtos (id, nome, categoria, preco, ingredientes)
VALUES (nextval('produtos_id_seq'), 'Caesar Salad', 'ACOMPANHAMENTO', 12.00, 'Romaine, Parmesan, Croutons, Caesar Dressing');

INSERT INTO produtos (id, nome, categoria, preco, ingredientes)
VALUES (nextval('produtos_id_seq'), 'Spaghetti Carbonara', 'ACOMPANHAMENTO', 18.00, 'Spaghetti, Eggs, Pancetta, Parmesan, Black Pepper');

INSERT INTO produtos (id, nome, categoria, preco, ingredientes)
VALUES (nextval('produtos_id_seq'), 'Chocolate Cake', 'SOBREMESA', 8.00, 'Flour, Sugar, Cocoa, Baking Powder, Eggs, Milk, Butter');

INSERT INTO produtos (id, nome, categoria, preco, ingredientes)
VALUES (nextval('produtos_id_seq'), 'x-burguer', 'LANCHE', 10.00, 'Beef, Cheese, Lettuce, Tomato, Bun');

INSERT INTO produtos (id, nome, categoria, preco, ingredientes)
VALUES (nextval('produtos_id_seq'), 'x-tudo', 'LANCHE', 10.00, 'Beef, Cheese, Lettuce, Tomato, Bun, egg, bacon');

INSERT INTO produtos (id, nome, categoria, preco, ingredientes)
VALUES (nextval('produtos_id_seq'), 'coca-cola lata', 'BEBIDA', 8.00, 'Beef, Cheese, Lettuce, Tomato, Bun, egg, bacon');
