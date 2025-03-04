package br.com.on.fiap.hexagono.usecase.pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.datapool.DataPoolPagamento;
import br.com.on.fiap.hexagono.datapool.DataPoolPedido;
import br.com.on.fiap.hexagono.domain.entity.Pagamento;
import br.com.on.fiap.hexagono.domain.entity.Pedido;
import br.com.on.fiap.hexagono.domain.entity.SituacaoPagamento;
import br.com.on.fiap.hexagono.domain.entity.TipoPagamento;
import br.com.on.fiap.hexagono.domain.exception.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.domain.exception.message.MessageError;
import br.com.on.fiap.hexagono.domain.exception.message.MessageManager;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoAtualizaUseCaseImplTest {

    @Mock
    private AtualizaPedidoPortaSaida atualizaPedidoPortaSaida;

    @InjectMocks
    private PedidoAtualizaUseCaseImpl atualizaPedidoCasoDeUsoImpl;

    @Test
    @DisplayName("Dado um pedido existente, ao atualizar, deve retornar o pedido atualizado")
    void dadoPedidoExistente_quandoAtualizarPedido_entaoDeveRetornarPedidoAtualizado() {
        String protocolo = "12345";
        Pedido pedidoExistente = DataPoolPedido.pedidoComProtocolo(protocolo);

        when(atualizaPedidoPortaSaida.atualizarPedido(protocolo)).thenReturn(Optional.of(pedidoExistente));

        Pedido pedidoAtualizado = atualizaPedidoCasoDeUsoImpl.atualizarPedido(protocolo);

        assertNotNull(pedidoAtualizado);
        assertEquals(protocolo, pedidoAtualizado.getProtocolo());
        verify(atualizaPedidoPortaSaida).atualizarPedido(protocolo);
    }

    @Test
    @DisplayName("Dado um pedido não existente, ao atualizar, deve lançar exceção PedidoNaoEncontradoExcecao")
    void dadoPedidoNaoExistente_quandoAtualizarPedido_entaoDeveLancarExcecao() {
        String protocolo = "99999";

        when(atualizaPedidoPortaSaida.atualizarPedido(protocolo)).thenReturn(Optional.empty());

        PedidoNaoEncontradoExcecao exception = assertThrows(
                PedidoNaoEncontradoExcecao.class, () -> atualizaPedidoCasoDeUsoImpl.atualizarPedido(protocolo));

        assertEquals(
                MessageManager.getMessage(
                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo),
                exception.getMessage());
        verify(atualizaPedidoPortaSaida).atualizarPedido(protocolo);
    }

    @Test
    @DisplayName(
            "Dado um pedido com pagamento existente, ao atualizar, deve retornar o pedido atualizado com pagamento")
    void dadoPedidoComPagamentoExistente_quandoAtualizarPedido_entaoDeveRetornarPedidoAtualizado() {
        Pagamento pagamento = DataPoolPagamento.pagamentoExistente(1L);

        String protocolo = "12345";
        Pedido pedidoExistente = DataPoolPedido.pedidoComProtocolo(protocolo);
        pedidoExistente.setPagamento(pagamento);

        when(atualizaPedidoPortaSaida.atualizarPedido(protocolo)).thenReturn(Optional.of(pedidoExistente));

        Pedido pedidoAtualizado = atualizaPedidoCasoDeUsoImpl.atualizarPedido(protocolo);

        assertNotNull(pedidoAtualizado);
        assertEquals(protocolo, pedidoAtualizado.getProtocolo());
        assertEquals(pagamento.getPagId(), pedidoAtualizado.getPagamento().getPagId());
        verify(atualizaPedidoPortaSaida).atualizarPedido(protocolo);
    }

    @Test
    @DisplayName(
            "Dado um pedido com pagamento não efetuado, ao atualizar, deve lançar exceção PedidoNaoEncontradoExcecao")
    void dadoPedidoComPagamentoNaoEfetuado_quandoAtualizarPedido_entaoDeveLancarExcecao() {
        Pagamento pagamento =
                DataPoolPagamento.pagamentoComTipoESituacao(2L, TipoPagamento.DEBITO, SituacaoPagamento.PENDENTE);

        String protocolo = "99999";
        Pedido pedidoExistente = DataPoolPedido.pedidoComProtocolo(protocolo);
        pedidoExistente.setPagamento(pagamento);

        when(atualizaPedidoPortaSaida.atualizarPedido(protocolo)).thenReturn(Optional.empty());

        PedidoNaoEncontradoExcecao exception = assertThrows(
                PedidoNaoEncontradoExcecao.class, () -> atualizaPedidoCasoDeUsoImpl.atualizarPedido(protocolo));

        assertEquals(
                MessageManager.getMessage(
                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo),
                exception.getMessage());
        verify(atualizaPedidoPortaSaida).atualizarPedido(protocolo);
    }
}
