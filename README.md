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

### 1 - Crie um namespace (opcional):
```sh
export NAMESPACE=tech-challenge

kubectl create namespace $NAMESPACE
```

### 2 - Aplique os arquivos YAML:
```sh

# Configurando SECRETS
kubectl apply -f tech-challenge/srv-monolito/k8s/secrets/secrets.yaml -n $NAMESPACE

# Configurando METRICAS
kubectl apply -f tech-challenge/srv-monolito/k8s/metricas/clusterrolebinding.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/metricas/role.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/metricas/rolebinding.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/metricas/serviceaccount.yaml -n $NAMESPACE

#Configurando POSTGRES
kubectl apply -f tech-challenge/srv-monolito/k8s/postgres-config.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/postgres-service.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/postgres-deployment.yaml -n $NAMESPACE

# Configurando MERCADOPAGO_FAKE
kubectl apply -f tech-challenge/srv-monolito/k8s/mercadopagofake/k8s/hpa.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/mercadopagofake/k8s/mercadopagofake-config.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/mercadopagofake/k8s/mercadopagofake-deployment.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/mercadopagofake/k8s/mercadopagofake-service.yaml -n $NAMESPACE

# Configurando SRV-MONOLITO
kubectl apply -f tech-challenge/srv-monolito/k8s/srv-monolito/hpa.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/srv-monolito/srv-monolito-config.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/srv-monolito/srv-monolito-deployment.yaml -n $NAMESPACE
kubectl apply -f tech-challenge/srv-monolito/k8s/srv-monolito/srv-monolito-service.yaml -n $NAMESPACE

```

### 3 - Verifique os pods e serviços:
```sh
kubectl get all -n $NAMESPACE
```
___

## Criando Dashboard:
O Kubernetes Dashboard é uma UI de propósito geral, baseada na web, para clusters Kubernetes. Ele permite que os usuários gerenciem aplicativos em execução no cluster e solucionem problemas, bem como gerenciem o próprio cluster.
Veja mais em no repositório do [github dashboard](https://github.com/kubernetes/dashboard?tab=readme-ov-file).

### 1 - Adiciona o repositório kubernetes-dashboard
```sh
helm repo add kubernetes-dashboard https://kubernetes.github.io/dashboard/
```

### 2 - Deploy do Helm Chart
```sh
helm upgrade --install kubernetes-dashboard kubernetes-dashboard/kubernetes-dashboard --create-namespace --namespace kubernetes-dashboard --version 7.5.0
```

### 3 - Criação do NAMESPACE kubernetes-dashboard
```sh
kubectl create namespace kubernetes-dashboard
```


### 4 - Criação da Service Account
```sh
kubectl apply -f tech-challenge/srv-monolito/k8s/dashboard/dashboard-adminuser.yaml
```


### 5 - Criação da ClusterRoleBinding
```sh
kubectl apply -f tech-challenge/srv-monolito/k8s/dashboard/cluster-role-binding.yaml 
```


### 6 - Criação de secret para Token definitivo
```sh
kubectl apply -f tech-challenge/srv-monolito/k8s/dashboard/secret.yaml 
```


### 7 - Criação do Token de autenticação
```sh
kubectl get secret admin-user -n kubernetes-dashboard -o jsonpath={".data.token"} | base64 -d
```


### 8 - Para acessar o Dashboard
```sh
kubectl port-forward -n kubernetes-dashboard svc/kubernetes-dashboard-kong-proxy 8443:443
```

Agora, é só acessar o dashboard em [http://localhost:8443](http://localhost:8443), inserir o token e pronto!
___

## 📜 Licença

Este projeto está sob a licença MIT.
___