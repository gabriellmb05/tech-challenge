package br.com.on.fiap.core.usecase.pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.gateway.PedidoGateway;
import br.com.on.fiap.core.datapool.DataPoolPagamento;
import br.com.on.fiap.core.datapool.DataPoolPedido;
import br.com.on.fiap.core.domain.entity.Pagamento;
import br.com.on.fiap.core.domain.entity.Pedido;
import br.com.on.fiap.core.domain.entity.SituacaoPagamento;
import br.com.on.fiap.core.domain.entity.TipoPagamento;
import br.com.on.fiap.core.domain.exception.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.core.domain.exception.message.MessageError;
import br.com.on.fiap.core.domain.exception.message.MessageManager;
import br.com.on.fiap.core.usecase.pedido.impl.PedidoAtualizaUseCaseImpl;
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
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private PedidoAtualizaUseCaseImpl pedidoAtualizaUseCase;

    @Test
    @DisplayName("Dado um pedido existente, ao atualizar, deve retornar o pedido atualizado")
    void dadoPedidoExistente_quandoAtualizarPedido_entaoDeveRetornarPedidoAtualizado() {
        String protocolo = "12345";
        Pedido pedidoExistente = DataPoolPedido.pedidoComProtocolo(protocolo);

        when(pedidoGateway.atualizarPedido(protocolo)).thenReturn(Optional.of(pedidoExistente));

        Pedido pedidoAtualizado = pedidoAtualizaUseCase.atualizarPedido(protocolo);

        assertNotNull(pedidoAtualizado);
        assertEquals(protocolo, pedidoAtualizado.getProtocolo());
        verify(pedidoGateway).atualizarPedido(protocolo);
    }

    @Test
    @DisplayName("Dado um pedido não existente, ao atualizar, deve lançar exceção PedidoNaoEncontradoExcecao")
    void dadoPedidoNaoExistente_quandoAtualizarPedido_entaoDeveLancarExcecao() {
        String protocolo = "99999";

        when(pedidoGateway.atualizarPedido(protocolo)).thenReturn(Optional.empty());

        PedidoNaoEncontradoExcecao exception =
                assertThrows(PedidoNaoEncontradoExcecao.class, () -> pedidoAtualizaUseCase.atualizarPedido(protocolo));

        assertEquals(
                MessageManager.getMessage(
                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo),
                exception.getMessage());
        verify(pedidoGateway).atualizarPedido(protocolo);
    }

    @Test
    @DisplayName(
            "Dado um pedido com pagamento existente, ao atualizar, deve retornar o pedido atualizado com pagamento")
    void dadoPedidoComPagamentoExistente_quandoAtualizarPedido_entaoDeveRetornarPedidoAtualizado() {
        Pagamento pagamento = DataPoolPagamento.pagamentoExistente(1L);

        String protocolo = "12345";
        Pedido pedidoExistente = DataPoolPedido.pedidoComProtocolo(protocolo);
        pedidoExistente.setPagamento(pagamento);

        when(pedidoGateway.atualizarPedido(protocolo)).thenReturn(Optional.of(pedidoExistente));

        Pedido pedidoAtualizado = pedidoAtualizaUseCase.atualizarPedido(protocolo);

        assertNotNull(pedidoAtualizado);
        assertEquals(protocolo, pedidoAtualizado.getProtocolo());
        assertEquals(pagamento.getPagId(), pedidoAtualizado.getPagamento().getPagId());
        verify(pedidoGateway).atualizarPedido(protocolo);
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

        when(pedidoGateway.atualizarPedido(protocolo)).thenReturn(Optional.empty());

        PedidoNaoEncontradoExcecao exception =
                assertThrows(PedidoNaoEncontradoExcecao.class, () -> pedidoAtualizaUseCase.atualizarPedido(protocolo));

        assertEquals(
                MessageManager.getMessage(
                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo),
                exception.getMessage());
        verify(pedidoGateway).atualizarPedido(protocolo);
    }
}
