package br.com.on.fiap.core.application.usecase.pedido;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.application.gateway.PedidoGateway;
import br.com.on.fiap.core.application.usecase.pedido.impl.PedidoSalvaUseCaseImpl;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.datapool.PedidoDataPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoSalvaUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private PedidoSalvaUseCaseImpl pedidoSalvaUseCase;

    @Test
    @DisplayName("Dado um pedido, quando salvar, então o pedido deve ser salvo corretamente")
    void dadoPedido_quandoSalvarPedido_entaoPedidoDeveSerSalvo() {
        Pedido pedido = PedidoDataPool.criarPedidoComProdutosValidos();

        when(pedidoGateway.salvaPedido(pedido)).thenReturn(pedido);

        Pedido pedidoSalvo = pedidoSalvaUseCase.salvarPedido(pedido);

        assertNotNull(pedidoSalvo);
        verify(pedidoGateway).salvaPedido(pedido);
    }

    @Test
    @DisplayName("Quando salvar o pedido, então o pedido retornado deve ser o mesmo pedido passado para o gateway")
    void quandoSalvarPedido_entaoPedidoRetornadoDeveSerOMesmoPedidoPassado() {
        Pedido pedido = PedidoDataPool.criarPedidoComProdutosValidos();

        when(pedidoGateway.salvaPedido(pedido)).thenReturn(pedido);

        Pedido pedidoSalvo = pedidoSalvaUseCase.salvarPedido(pedido);

        assertSame(pedido, pedidoSalvo);
    }
}
