package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.adapter.gateway.base.ClienteGateway;
import br.com.on.fiap.hexagono.adapter.gateway.base.PagamentoGateway;
import br.com.on.fiap.hexagono.adapter.gateway.base.PedidoGateway;
import br.com.on.fiap.hexagono.usecase.pedido.PedidoAtualizaUseCaseImpl;
import br.com.on.fiap.hexagono.usecase.pedido.PedidoBuscaUseCaseImpl;
import br.com.on.fiap.hexagono.usecase.pedido.PedidoDetalhaUseCaseImpl;
import br.com.on.fiap.hexagono.usecase.pedido.PedidoInsereUseCaseImpl;
import br.com.on.fiap.hexagono.usecase.pedido.base.PedidoAtualizaUseCase;
import br.com.on.fiap.hexagono.usecase.pedido.base.PedidoBuscaUseCase;
import br.com.on.fiap.hexagono.usecase.pedido.base.PedidoDetalhaUseCase;
import br.com.on.fiap.hexagono.usecase.pedido.base.PedidoInsereUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PedidoBeanConfiguracao {

    private final ClienteGateway clienteGateway;
    private final PedidoGateway pedidoGateway;
    private final PagamentoGateway pagamentoGateway;

    @Bean
    public PedidoInsereUseCase inserePedido() {
        return new PedidoInsereUseCaseImpl(clienteGateway, pedidoGateway, pagamentoGateway);
    }

    @Bean
    public PedidoBuscaUseCase listaPedidos() {
        return new PedidoBuscaUseCaseImpl(pedidoGateway);
    }

    @Bean
    public PedidoDetalhaUseCase detalhaPedido() {
        return new PedidoDetalhaUseCaseImpl(pedidoGateway);
    }

    @Bean
    public PedidoAtualizaUseCase atualizaPedido() {
        return new PedidoAtualizaUseCaseImpl(pedidoGateway);
    }
}
