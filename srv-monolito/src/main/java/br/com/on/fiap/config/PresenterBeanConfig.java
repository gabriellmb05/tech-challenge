package br.com.on.fiap.config;

import br.com.on.fiap.core.adapter.presenter.*;
import br.com.on.fiap.core.adapter.presenter.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PresenterBeanConfig {

    @Bean
    public CategoriaPresenter categoriaPresenter() {
        return new CategoriaPresenterImpl();
    }

    @Bean
    public ClientePresenter clientePresenter() {
        return new ClientePresenterImpl();
    }

    @Bean
    public PagamentoPresenter pagamentoPresenter() {
        return new PagamentoPresenterImpl();
    }

    @Bean
    public PedidoPresenter pedidoPresenter() {
        return new PedidoPresenterImpl();
    }

    @Bean
    public ProdutoPresenter produtoPresenter() {
        return new ProdutoPresenterImpl();
    }
}
