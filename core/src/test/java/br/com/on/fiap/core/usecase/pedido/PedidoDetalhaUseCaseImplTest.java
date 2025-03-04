package br.com.on.fiap.core.usecase.pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.gateway.PedidoGateway;
import br.com.on.fiap.core.datapool.DataPoolPedido;
import br.com.on.fiap.core.domain.entity.Pedido;
import br.com.on.fiap.core.domain.exception.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.core.usecase.pedido.impl.PedidoDetalhaUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoDetalhaUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private PedidoDetalhaUseCaseImpl pedidoDetalhaUseCase;

    @Test
    @DisplayName("Dado um protocolo válido, ao detalhar o pedido, o pedido deve ser retornado")
    void dadoProtocoloValido_quandoDetalharPedido_entaoPedidoDeveSerRetornado() {
        String protocolo = "20250118213724238248";
        Pedido pedido = DataPoolPedido.pedidoExistente(1L);

        when(pedidoGateway.detalhaPedido(protocolo)).thenReturn(java.util.Optional.of(pedido));

        Pedido result = pedidoDetalhaUseCase.detalhaPedido(protocolo);

        assertNotNull(result);
        assertEquals(protocolo, result.getProtocolo());
        verify(pedidoGateway).detalhaPedido(protocolo);
    }

    @Test
    @DisplayName("Dado um protocolo inválido, ao detalhar o pedido, deve lançar a exceção PedidoNaoEncontradoExcecao")
    void dadoProtocoloInvalido_quandoDetalharPedido_entaoPedidoNaoEncontradoExcecaoDeveSerLancada() {
        String protocoloInvalido = "PROTOCOLO_INEXISTENTE";

        when(pedidoGateway.detalhaPedido(protocoloInvalido)).thenReturn(java.util.Optional.empty());

        PedidoNaoEncontradoExcecao exception = assertThrows(
                PedidoNaoEncontradoExcecao.class, () -> pedidoDetalhaUseCase.detalhaPedido(protocoloInvalido));

        assertEquals("Não foi encontrado o pedido para protocolo: " + protocoloInvalido, exception.getMessage());
        verify(pedidoGateway).detalhaPedido(protocoloInvalido);
    }
}
