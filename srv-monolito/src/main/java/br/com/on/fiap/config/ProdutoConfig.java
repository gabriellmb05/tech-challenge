package br.com.on.fiap.config;

import br.com.on.fiap.hexagono.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.hexagono.application.usecase.produto.*;
import br.com.on.fiap.hexagono.application.usecase.produto.impl.*;
import br.com.on.fiap.hexagono.usecase.produto.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

    private final ProdutoGateway produtoGateway;

    public ProdutoConfig(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Bean
    public ProdutoBuscaPorIdUseCase buscaProduto() {
        return new ProdutoBuscaPorIdUseCaseImpl(produtoGateway);
    }

    @Bean
    public ProdutoInsereUseCase insereProduto() {
        return new ProdutoInsereUseCaseImpl(produtoGateway);
    }

    @Bean
    public ProdutoAlteraUseCase alteraProduto() {
        return new ProdutoAlteraUseCaseImpl(produtoGateway);
    }

    @Bean
    public ProdutoDeletaUseCase deletaProduto() {
        return new ProdutoDeletaUseCaseImpl(produtoGateway);
    }

    @Bean
    public ProdutoListaUseCase listarProduto() {
        return new ProdutoListaUseCaseImpl(produtoGateway);
    }

    @Bean
    public ProdutoValidaPedidoUseCase validaProdutosCasoDeUso() {
        return new ProdutoValidaPedidoUseCaseImpl(produtoGateway);
    }
}
