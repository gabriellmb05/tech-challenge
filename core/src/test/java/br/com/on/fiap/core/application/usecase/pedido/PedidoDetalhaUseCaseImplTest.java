package br.com.on.fiap.core.application.usecase.pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.application.exception.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.gateway.PedidoGateway;
import br.com.on.fiap.core.application.usecase.pedido.impl.PedidoDetalhaUseCaseImpl;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.datapool.PedidoDataPool;
import java.util.Optional;
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
        Pedido pedido = PedidoDataPool.criarPedidoExistente(1L);

        when(pedidoGateway.detalhaPedido(protocolo)).thenReturn(Optional.of(pedido));

        Pedido result = pedidoDetalhaUseCase.detalhaPedido(protocolo);

        assertNotNull(result);
        assertEquals(protocolo, result.getProtocolo());
        verify(pedidoGateway).detalhaPedido(protocolo);
    }

    @Test
    @DisplayName("Dado um protocolo inválido, ao detalhar o pedido, deve lançar a exceção PedidoNaoEncontradoExcecao")
    void dadoProtocoloInvalido_quandoDetalharPedido_entaoPedidoNaoEncontradoExcecaoDeveSerLancada() {
        String protocoloInvalido = "PROTOCOLO_INEXISTENTE";

        when(pedidoGateway.detalhaPedido(protocoloInvalido)).thenReturn(Optional.empty());

        PedidoNaoEncontradoExcecao exception = assertThrows(
                PedidoNaoEncontradoExcecao.class, () -> pedidoDetalhaUseCase.detalhaPedido(protocoloInvalido));

        assertEquals("Não foi encontrado o pedido para protocolo: " + protocoloInvalido, exception.getMessage());
        verify(pedidoGateway).detalhaPedido(protocoloInvalido);
    }
}
