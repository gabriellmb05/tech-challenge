CREATE TABLE clientes (
  id SERIAL PRIMARY KEY,
  nome VARCHAR (256) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  email VARCHAR(100) NOT NULL,
  dataNascimento VARCHAR(20)
);

INSERT INTO clientes (nome, cpf, email, dataNascimento)
VALUES
  ('Elmo Cameron','433166526-16','lectus@yahoo.edu','2025-03-28'),
  ('Phelan Vang','473747385-23','ut.molestie@google.edu','2024-07-14'),
  ('Lacey Britt','494734764-86','ullamcorper@icloud.com','2025-05-11'),
  ('Moses Gaines','536253674-24','luctus@icloud.couk','2024-05-13'),
  ('Samantha Foreman','954157471-65','2025-06-25','viverra@hotmail.ca'),
  ('Alyssa Sutton','848767856-74','amet.consectetuer@yahoo.org','2025-06-16'),
  ('Kevyn Hill','788792761-62','pharetra.nam@hotmail.edu','2025-08-02'),
  ('Wang May','884482536-14','magna.et@icloud.couk','2024-08-19'),
  ('Hilda Shaffer','472836288-27','et.commodo@aol.ca','2024-12-15'),
  ('Len Frank','518679432-59','eu.enim.etiam@protonmail.net','2024-08-29');

CREATE TABLE produtos (
  id SERIAL PRIMARY KEY,
  nome TEXT NOT NULL,
  categoria TEXT NOT NULL,
  preco varchar(100) NOT NULL,
  ingredientes TEXT NULL
);

INSERT INTO produtos (nome,categoria,preco,ingredientes)
VALUES
  ('diam.','Acompanhamento','84','nec ligula consectetuer rhoncus. Nullam velit dui, semper et,'),
  ('erat. Sed','Acompanhamento','91','mi. Aliquam gravida mauris'),
  ('Aliquam','Bebida','10','Mauris vestibulum, neque sed dictum eleifend, nunc'),
  ('ultrices iaculis odio.','Acompanhamento','81','Sed nunc est, mollis non, cursus non, egestas'),
  ('Aliquam erat','Bebida','97','nulla. Integer urna. Vivamus molestie dapibus ligula. Aliquam'),
  ('eleifend','Bebida','58','a purus. Duis elementum, dui quis'),
  ('vel arcu.','Bebida','84','lorem. Donec elementum, lorem ut aliquam iaculis, lacus'),
  ('erat','Lanche','41','magna tellus'),
  ('magnis dis parturient','Lanche','17','Mauris magna. Duis dignissim tempor arcu.'),
  ('quam vel','Acompanhamento','84','vitae, sodales at, velit. Pellentesque');
INSERT INTO Produtos (nome,categoria,preco,ingredientes)
VALUES
  ('Sed nunc est,','Acompanhamento','11','Suspendisse tristique neque venenatis lacus.'),
  ('varius orci,','Acompanhamento','73','velit. Aliquam nisl. Nulla eu'),
  ('vitae sodales','Lanche','67','euismod urna. Nullam lobortis quam a felis ullamcorper viverra.'),
  ('nascetur ridiculus','Bebida','22','orci. Donec nibh. Quisque nonummy ipsum'),
  ('ullamcorper, nisl arcu','Bebida','16','faucibus orci luctus et ultrices'),
  ('cursus.','Acompanhamento','63','eros turpis non enim. Mauris quis'),
  ('euismod urna.','Acompanhamento','89','nulla. Cras eu tellus eu augue porttitor interdum. Sed auctor'),
  ('mattis ornare,','Acompanhamento','68','Duis ac arcu. Nunc mauris. Morbi non sapien'),
  ('hendrerit a, arcu.','Acompanhamento','18','lacus vestibulum'),
  ('lorem, sit','Lanche','52','Integer vitae nibh. Donec est mauris,');
INSERT INTO Produtos (nome,categoria,preco,ingredientes)
VALUES
  ('Integer vitae nibh.','Bebida','89','Phasellus libero mauris, aliquam eu, accumsan sed,'),
  ('Quisque tincidunt','Lanche','62','primis in faucibus orci luctus'),
  ('ante. Maecenas','Bebida','37','neque tellus, imperdiet non,'),
  ('urna. Ut','Acompanhamento','17','est arcu ac'),
  ('arcu. Vivamus','Lanche','94','quis diam luctus lobortis. Class aptent taciti'),
  ('Donec','Acompanhamento','71','Donec'),
  ('purus. Maecenas','Lanche','68','leo. Cras vehicula aliquet libero. Integer in magna.'),
  ('porttitor vulputate,','Lanche','36','sem ut cursus luctus, ipsum leo elementum sem,'),
  ('mauris,','Lanche','75','lacinia vitae,'),
  ('aliquet','Bebida','56','lectus convallis est, vitae sodales nisi');