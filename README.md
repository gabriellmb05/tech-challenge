# Tech Challenge 01

## Visão Geral

Este projeto é uma aplicação Java utilizando Maven para gerenciamento de dependências. A aplicação segue uma arquitetura
hexagonal e é estruturada de forma modular, com múltiplos serviços para gerenciar funcionalidades distintas. A aplicação
está organizada de forma monolítica, com todos os serviços interligados em um único projeto, mas ainda assim respeitando
a modularidade interna para facilitar a evolução e escalabilidade.

![Java](documentacao/java.svg)
![Spring](documentacao/spring.svg)
![Maven](documentacao/apache_maven.svg)
![Docker](documentacao/docker.svg)
![Postgres](documentacao/postgres.svg)

## Estrutura do Projeto

| Módulo         | Descrição                                                                  |
|----------------|----------------------------------------------------------------------------|
| `hexagono`     | Contém a lógica de negócio e interfaces, seguindo a arquitetura hexagonal. |
| `srv-monolito` | Módulo monolítico que integra todos os serviços e funcionalidades.         |

## Arquitetura

A aplicação segue a arquitetura hexagonal (também chamada de "Ports and Adapters"), onde a lógica de negócio (núcleo) é
isolada da comunicação com o mundo externo (banco de dados, interfaces de usuário, APIs externas).

![Arquitetura Hexagonal](documentacao/hexagonal_architecture.png)

## Pré-requisitos

- Java 21
- Maven 3.9.9
- Docker

## Configuração de Variáveis de Ambiente

Certifique-se de configurar o arquivo `.env` com as seguintes variáveis de ambiente antes de construir e executar o
projeto:

| Variável               | Descrição                           |
|------------------------|-------------------------------------|
| `DB_NAME`              | Nome do banco de dados              |
| `DB_USER`              | Usuário do banco de dados           |
| `DB_PASSWORD`          | Senha do banco de dados             |
| `DB_URL`               | URL de conexão com o banco de dados |
| `DB_DRIVER_CLASS_NAME` | Classe do driver do banco de dados  |
| `FLYWAY_ENABLE`        | Habilita ou desabilita o Flyway     |
| `LOG_LEVEL_ROOT`       | Nível de log da aplicação           |
| `IT_PAGAMENTO`         | Integração com api-pagamentos       |

Exemplo de configuração do arquivo `.env`:

```dotenv
DB_NAME=tech-challenge
DB_USER=tech-challenge
DB_PASSWORD=tech-challenge
DB_URL=jdbc:postgresql://localhost:5432/tech-challenge
DB_DRIVER_CLASS_NAME=org.postgresql.Driver
FLYWAY_ENABLE=true
LOG_LEVEL_ROOT=INFO
```

## Documentação da API

![Swagger](documentacao/swagger.svg)

A documentação da API pode ser acessada através do Swagger em `http://localhost:8080/documentacao.html`.

## Construir e Rodar os Contêineres

Execute os seguintes comandos para construir e iniciar os contêineres Docker:

```shell
docker-compose up --build
```

## Formatação de Código

Este projeto utiliza o Spotless Maven Plugin para garantir que o código esteja formatado de acordo com as convenções
definidas. O plugin pode ser configurado para garantir que o código tenha uma formatação consistente.

- Para verificar se o código segue as convenções de formatação, basta rodar o seguinte comando Maven:

```shell
mvn spotless:check
```

- Formatar o código de acordo com as convenções

```shell
mvn spotless:apply
```

## Relatório de Cobertura de Testes com Jacoco

Este projeto utiliza o Jacoco Maven Plugin para gerar relatórios de cobertura de testes. O Jacoco é uma ferramenta de
análise de cobertura de código, que gera um relatório detalhado mostrando quais partes do código foram testadas.

- Para gerar o relatório de cobertura de testes com Jacoco, basta rodar o seguinte comando Maven:

```shell
mvn clean test
```

O relatório gerado estará localizado na pasta `target/site/jacoco/index.html` Para visualizar o relatório, basta abrir
esse arquivo em um navegador:
