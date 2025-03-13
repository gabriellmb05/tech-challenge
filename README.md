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
| `POSTGRES_DB`              | Nome do banco de dados              |
| `POSTGRES_USER`              | Usuário do banco de dados           |
| `POSTGRES_PASSWORD`          | Senha do banco de dados             |
| `DB_URL`               | URL de conexão com o banco de dados |
| `DB_DRIVER_CLASS_NAME` | Classe do driver do banco de dados  |
| `FLYWAY_ENABLE`        | Habilita ou desabilita o Flyway     |
| `LOG_LEVEL_ROOT`       | Nível de log da aplicação           |
| `IT_PAGAMENTO`         | Integração com api-pagamentos       |

Exemplo de configuração do arquivo `.env`:

```dotenv
POSTGRES_DB=tech-challenge
POSTGRES_USER=tech-challenge
POSTGRES_PASSWORD=tech-challenge
DB_URL=jdbc:postgresql://db:5432/tech-challenge
DB_DRIVER_CLASS_NAME=org.postgresql.Driver
FLYWAY_ENABLE=true
LOG_LEVEL_ROOT=INFO
IT_PAGAMENTO=http://mercadopagofake:8081/api
```

## Construir e Rodar os Contêineres

Execute os seguintes comandos para construir e iniciar os contêineres Docker:
```shell
mvn spotless:apply
```
```shell
docker-compose up --build
```

## Documentação da API

![Swagger](documentacao/swagger.svg)

A documentação da API pode ser acessada através do Swagger em `http://localhost:8080/documentacao.html`.

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

___

## 🚀 Como Aplicar no Kubernetes

### 1️⃣ Crie um namespace (opcional):
```sh
export NAMESPACE=tech-challenge

kubectl create namespace $NAMESPACE
```

### 2️⃣ Aplique os arquivos YAML:
```sh

# Configurando SECRETS"
kubectl apply -f tech-challenge/srv-monolito/k8s/secrets.yaml -n $NAMESPACE

# Configurando CONFIGS
kubectl apply -f tech-challenge/srv-monolito/k8s/postgres-config.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/srv-monolito-config.yaml -n $NAMESPACE
kubectl apply -f mock_payment/mercadopago_fake/k8s/mercadopagofake-config.yaml -n $NAMESPACE

# Configurando mapeamento de pastas para o banco de dados
mkdir -p docker/osdsk8s/postgres-data

# Configurando DEPLOYMENTS
kubectl apply -f tech-challenge/srv-monolito/k8s/postgres-deployment.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/srv-monolito-deployment.yaml -n $NAMESPACE
kubectl apply -f mock_payment/mercadopago_fake/k8s/mercadopagofake-deployment.yaml -n $NAMESPACE

# Configurando SERVICES
kubectl apply -f tech-challenge/srv-monolito/k8s/postgres-service.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/srv-monolito-service.yaml -n $NAMESPACE
kubectl apply -f mock_payment/mercadopago_fake/k8s/mercadopagofake-service.yaml -n $NAMESPACE

```

### 3️⃣ Verifique os pods e serviços:
```sh
kubectl get all -n $NAMESPACE
```
___

## 📜 Licença

Este projeto está sob a licença MIT.
___