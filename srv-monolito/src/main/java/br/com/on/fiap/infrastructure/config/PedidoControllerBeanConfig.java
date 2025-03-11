package br.com.on.fiap.infrastructure.config;

import br.com.on.fiap.core.adapter.controller.PedidoController;
import br.com.on.fiap.core.adapter.controller.impl.PedidoControllerImpl;
import br.com.on.fiap.core.adapter.presenter.PedidoPresenter;
import br.com.on.fiap.core.application.usecase.pedido.PedidoAtualizaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoInsereUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoListaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PedidoControllerBeanConfig {

    private final PedidoInsereUseCase pedidoInsereUseCase;
    private final PedidoListaUseCase pedidoListaUseCase;
    private final PedidoDetalhaUseCase pedidoDetalhaUseCase;
    private final PedidoAtualizaUseCase pedidoAtualizaUseCase;
    private final PedidoPresenter pedidoPresenter;

    @Bean
    public PedidoController pedidoController() {
        return new PedidoControllerImpl(
                pedidoInsereUseCase, pedidoListaUseCase, pedidoDetalhaUseCase, pedidoAtualizaUseCase, pedidoPresenter);
    }
}
