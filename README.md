# Tech Challenge 01

## Visão Geral

Este projeto é uma aplicação baseada em Java que utiliza Maven para gerenciamento de dependências. A aplicação segue uma arquitetura hexagonal e inclui vários módulos e serviços.

## Estrutura do Projeto

- `tech-challenge-01`: Projeto pai
- `hexagono`: Módulo que contém a lógica de negócio e interfaces
- `srv-produto`: Módulo de serviço para gerenciamento de produtos

## Pré-requisitos

- Java 21
- Maven 3.9.9

## Construindo o Projeto

Para construir o projeto, execute o seguinte comando a partir do diretório raiz:

```sh
mvn clean package