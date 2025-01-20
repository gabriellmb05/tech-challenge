package br.com.on.fiap.hexagono.casosdeuso.pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.datapool.DataPoolPagamento;
import br.com.on.fiap.hexagono.datapool.DataPoolPedido;
import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.SituacaoPagamento;
import br.com.on.fiap.hexagono.dominio.TipoPagamento;
import br.com.on.fiap.hexagono.excecao.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.excecao.message.MessageManager;
import br.com.on.fiap.hexagono.portas.saida.pedido.AtualizaPedidoPortaSaida;
import java.util.Optional;

import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPagamentoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AtualizaPedidoCasoDeUsoTest {

    @Mock
    private AtualizaPedidoPortaSaida atualizaPedidoPortaSaida;

    @Mock
    private PersistePedidoPagamentoPortaSaida persistePedidoPagamentoPortaSaida;

    @InjectMocks
    private AtualizaPedidoCasoDeUso atualizaPedidoCasoDeUso;

//    @Test
//    @DisplayName("Dado um pedido existente, ao atualizar, deve retornar o pedido atualizado")
//    void dadoPedidoExistente_quandoAtualizarPedido_entaoDeveRetornarPedidoAtualizado() {
//        String protocolo = "12345";
//        Pedido pedidoExistente = DataPoolPedido.pedidoComProtocolo(protocolo);
//        when(atualizaPedidoPortaSaida.buscaPedido(protocolo)).thenReturn(Optional.of(pedidoExistente));
//        doNothing().when(persistePedidoPagamentoPortaSaida).salvaPedidoPagamento(pedidoExistente);
//
//        Pedido pedidoAtualizado = atualizaPedidoCasoDeUso.atualizarPedido(protocolo);
//
//        assertNotNull(pedidoAtualizado);
//        assertEquals(protocolo, pedidoAtualizado.getProtocolo());
//        verify(atualizaPedidoPortaSaida).buscaPedido(protocolo);
//        verify(persistePedidoPagamentoPortaSaida).salvaPedidoPagamento(pedidoExistente);
//    }
//
//    @Test
//    @DisplayName("Dado um pedido não existente, ao atualizar, deve lançar exceção PedidoNaoEncontradoExcecao")
//    void dadoPedidoNaoExistente_quandoAtualizarPedido_entaoDeveLancarExcecao() {
//        String protocolo = "99999";
//
//        when(atualizaPedidoPortaSaida.buscaPedido(protocolo)).thenReturn(Optional.empty());
//
//        PedidoNaoEncontradoExcecao exception = assertThrows(
//                PedidoNaoEncontradoExcecao.class, () -> atualizaPedidoCasoDeUso.atualizarPedido(protocolo));
//
//        assertEquals(
//                MessageManager.getMessage(
//                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo),
//                exception.getMessage());
//        verify(atualizaPedidoPortaSaida).buscaPedido(protocolo);
//    }
//
//    @Test
//    @DisplayName(
//            "Dado um pedido com pagamento existente, ao atualizar, deve retornar o pedido atualizado com pagamento")
//    void dadoPedidoComPagamentoExistente_quandoAtualizarPedido_entaoDeveRetornarPedidoAtualizado() {
//        Pagamento pagamento = DataPoolPagamento.pagamentoExistente(1L);
//
//        String protocolo = "12345";
//        Pedido pedidoExistente = DataPoolPedido.pedidoComProtocolo(protocolo);
//        pedidoExistente.setPagamento(pagamento);
//        when(atualizaPedidoPortaSaida.buscaPedido(protocolo)).thenReturn(Optional.of(pedidoExistente));
//        doNothing().when(persistePedidoPagamentoPortaSaida).salvaPedidoPagamento(pedidoExistente);
//
//        Pedido pedidoAtualizado = atualizaPedidoCasoDeUso.atualizarPedido(protocolo);
//
//        assertNotNull(pedidoAtualizado);
//        assertEquals(protocolo, pedidoAtualizado.getProtocolo());
//        assertEquals(pagamento.getPagId(), pedidoAtualizado.getPagamento().getPagId());
//        verify(atualizaPedidoPortaSaida).buscaPedido(protocolo);
//    }
//
//    @Test
//    @DisplayName(
//            "Dado um pedido com pagamento não efetuado, ao atualizar, deve lançar exceção PedidoNaoEncontradoExcecao")
//    void dadoPedidoComPagamentoNaoEfetuado_quandoAtualizarPedido_entaoDeveLancarExcecao() {
//        Pagamento pagamento =
//                DataPoolPagamento.pagamentoComTipoESituacao(2L, TipoPagamento.DEBITO, SituacaoPagamento.PENDENTE);
//
//        String protocolo = "99999";
//        Pedido pedidoExistente = DataPoolPedido.pedidoComProtocolo(protocolo);
//        pedidoExistente.setPagamento(pagamento);
//        when(atualizaPedidoPortaSaida.buscaPedido(protocolo)).thenReturn(Optional.empty());
//
//        PedidoNaoEncontradoExcecao exception = assertThrows(
//                PedidoNaoEncontradoExcecao.class, () -> atualizaPedidoCasoDeUso.atualizarPedido(protocolo));
//
//        assertEquals(
//                MessageManager.getMessage(
//                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo),
//                exception.getMessage());
//        verify(atualizaPedidoPortaSaida).buscaPedido(protocolo);
//    }
}
