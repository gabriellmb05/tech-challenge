### Para subir a aplicação java e o banco de dados são necessários apenas dois passos:
1. Alterar o arquivo .env adicionando os valores para as variáveis `POSTGRES_PASSWORD` e `POSTGRES_ROOT_PASSWORD`;
2. Executar o comando `docker compose up --build`;
3. Para validar se a aplicação e banco de dados subiram correramente você pode acessar no browser os endpoints abaixo. O endpoint `/clientes` realiza um consulta no banco de dados. 
   - `localhost:8080/hello-world`
   - `localhost:8080/clientes`

## Estrutura de pastas
`├── postgres

├── src

│   ├── main

│   │   ├── java

│   │   │   └── br

│   │   │       └── com

│   │   │           └── on

│   │   │               └── fiap

│   │   │                   ├── aplicacao

│   │   │                   │   └── adaptadores

│   │   │   │                   │       ├── controladores

│   │   │   │                   │       └── dtos

│   │   │                   ├── dominio

│   │   │                   │   ├── adaptadores

│   │   │                   │   └── portas

│   │   │                   │       ├── interfaces

│   │   │                   │       └── repositorios

│   │   │                   └── infraestrutura

│   │   │                       ├── adaptadores

│   │   │                       │   └── repositorios

│   │   │                       └── configuracao

│   │   └── resources

│   │       └── application.properties

`




