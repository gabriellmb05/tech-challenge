package br.com.on.fiap.hexagono.casosdeuso.pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.datapool.DataPoolPedido;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.excecao.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.portas.saida.pedido.DetalhaPedidoPortaSaida;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DetalhaPedidoCasoDeUsoTest {

    @Mock
    private DetalhaPedidoPortaSaida detalhaPedidoPortaSaida;

    @InjectMocks
    private DetalhaPedidoCasoDeUso detalhaPedidoCasoDeUso;

    @Test
    @DisplayName("Dado um protocolo válido, ao detalhar o pedido, o pedido deve ser retornado")
    void dadoProtocoloValido_quandoDetalharPedido_entaoPedidoDeveSerRetornado() {
        String protocolo = "20250118213724238248";
        Pedido pedido = DataPoolPedido.pedidoExistente(1L);

        when(detalhaPedidoPortaSaida.detalhaPedido(protocolo)).thenReturn(java.util.Optional.of(pedido));

        Pedido result = detalhaPedidoCasoDeUso.detalhaPedido(protocolo);

        assertNotNull(result);
        assertEquals(protocolo, result.getProtocolo());
        verify(detalhaPedidoPortaSaida).detalhaPedido(protocolo);
    }

    @Test
    @DisplayName("Dado um protocolo inválido, ao detalhar o pedido, deve lançar a exceção PedidoNaoEncontradoExcecao")
    void dadoProtocoloInvalido_quandoDetalharPedido_entaoPedidoNaoEncontradoExcecaoDeveSerLancada() {
        String protocoloInvalido = "PROTOCOLO_INEXISTENTE";

        when(detalhaPedidoPortaSaida.detalhaPedido(protocoloInvalido)).thenReturn(java.util.Optional.empty());

        PedidoNaoEncontradoExcecao exception = assertThrows(
                PedidoNaoEncontradoExcecao.class, () -> detalhaPedidoCasoDeUso.detalhaPedido(protocoloInvalido));

        assertEquals("Não foi encontrado o pedido para protocolo: " + protocoloInvalido, exception.getMessage());
        verify(detalhaPedidoPortaSaida).detalhaPedido(protocoloInvalido);
    }
}
