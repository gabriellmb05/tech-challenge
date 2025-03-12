package br.com.on.fiap.core.application.usecase.pagamento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.application.exception.PagamentoJaRealizadoExcecao;
import br.com.on.fiap.core.application.gateway.PagamentoGateway;
import br.com.on.fiap.core.application.gateway.PagamentoIntegracaoGateway;
import br.com.on.fiap.core.application.usecase.pagamento.impl.PagamentoAtualizaUseCaseImpl;
import br.com.on.fiap.core.application.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.core.domain.SituacaoPagamento;
import br.com.on.fiap.datapool.PedidoDataPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PagamentoAtualizaUseCaseImplTest {

    @Mock
    private PedidoDetalhaUseCase pedidoDetalhaUseCase;

    @Mock
    private PagamentoValidaUseCase pagamentoValidaUseCase;

    @Mock
    private PagamentoGateway pagamentoGateway;

    @Mock
    private PagamentoIntegracaoGateway pagamentoIntegracaoGateway;

    @InjectMocks
    private PagamentoAtualizaUseCaseImpl pagamentoAtualizaUseCase;

    @Test
    @DisplayName("Quando o pagamento for atualizado, então ele deve ser enviado e salvo corretamente")
    void quandoAtualizarPagamento_entaoPagamentoDeveSerEnviadoESalvo() {
        String nrProtocolo = "12345";
        Pedido pedido = PedidoDataPool.criarPedidoExistente(1L);
        when(pedidoDetalhaUseCase.detalhaPedido(nrProtocolo)).thenReturn(pedido);
        when(pagamentoGateway.salvaPagamento(any())).thenReturn(pedido.getPagamento());

        Pagamento resultado = pagamentoAtualizaUseCase.atualizaPagamento(nrProtocolo);

        verify(pedidoDetalhaUseCase).detalhaPedido(nrProtocolo);
        verify(pagamentoValidaUseCase).validarPagamentoJaRealizado(pedido.getPagamento(), nrProtocolo);
        verify(pagamentoIntegracaoGateway).integracaoEnviaPagamento(pedido.getPagamento());
        verify(pagamentoGateway).salvaPagamento(resultado);

        assertNotNull(resultado);
        assertEquals(SituacaoPagamento.APROVADO, resultado.getStPagamento());
        assertNotNull(resultado.getDhPagamento());
    }

    @Test
    @DisplayName("Quando o pagamento já foi realizado, então deve lançar uma exceção")
    void quandoPagamentoJaRealizado_entaoDeveLancarExcecao() {
        String nrProtocolo = "12345";

        Pedido pedido = PedidoDataPool.criarPedidoComProtocoloPagamentoValido(nrProtocolo);
        when(pedidoDetalhaUseCase.detalhaPedido(nrProtocolo)).thenReturn(pedido);
        doThrow(PagamentoJaRealizadoExcecao.class)
                .when(pagamentoValidaUseCase)
                .validarPagamentoJaRealizado(any(), any());
        assertThrows(PagamentoJaRealizadoExcecao.class, () -> pagamentoAtualizaUseCase.atualizaPagamento(nrProtocolo));

        verify(pedidoDetalhaUseCase).detalhaPedido(nrProtocolo);
        verify(pagamentoValidaUseCase).validarPagamentoJaRealizado(pedido.getPagamento(), nrProtocolo);
        verifyNoInteractions(pagamentoIntegracaoGateway, pagamentoGateway);
    }
}
