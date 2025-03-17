package br.com.on.fiap.core.adapter.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.adapter.controller.impl.PagamentoControllerImpl;
import br.com.on.fiap.core.adapter.presenter.PagamentoPresenter;
import br.com.on.fiap.core.application.dto.resposta.PagamentoResposta;
import br.com.on.fiap.core.application.exception.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoAtualizaUseCase;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.datapool.PagamentoDataPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PagamentoControllerImplTest {

    @Mock
    private PagamentoAtualizaUseCase pagamentoAtualizaUseCase;

    @Mock
    private PagamentoPresenter pagamentoPresenter;

    @InjectMocks
    private PagamentoControllerImpl pagamentoController;

    @Test
    @DisplayName(
            "Dado um número de protocolo, quando atualizar o pagamento, então o pagamento atualizado deve ser retornado no formato esperado")
    void dadoNumeroDeProtocolo_quandoAtualizarPagamento_entaoPagamentoAtualizadoDeveSerRetornado() {
        String nrProtocolo = "20250118213724238248";
        Pagamento pagamento = PagamentoDataPool.criarPagamentoParaAtualizacao();
        PagamentoResposta pagamentoRespostaEsperado = PagamentoResposta.create(pagamento);
        when(pagamentoAtualizaUseCase.atualizaPagamento(nrProtocolo)).thenReturn(pagamento);
        when(pagamentoPresenter.formatar(any(Pagamento.class))).thenReturn(pagamentoRespostaEsperado);

        PagamentoResposta resultado = pagamentoController.atualizaPagamento(nrProtocolo);

        assertNotNull(resultado);
        assertEquals(pagamentoRespostaEsperado.getId(), resultado.getId());
        assertEquals(pagamentoRespostaEsperado.getVlCompra(), resultado.getVlCompra());
        assertEquals(pagamentoRespostaEsperado.getStPagamento(), resultado.getStPagamento());
        assertEquals(pagamentoRespostaEsperado.getTpPagamento(), resultado.getTpPagamento());
        verify(pagamentoAtualizaUseCase).atualizaPagamento(nrProtocolo);
        verify(pagamentoPresenter).formatar(any(Pagamento.class));
    }

    @Test
    @DisplayName(
            "Dado um número de protocolo inválido, quando atualizar o pagamento, então uma exceção deve ser lançada")
    void dadoNumeroDeProtocoloInvalido_quandoAtualizarPagamento_entaoExcecaoDeveSerLancada() {
        String nrProtocoloInvalido = "9999999999999999";

        doThrow(PedidoNaoEncontradoExcecao.class).when(pagamentoAtualizaUseCase).atualizaPagamento(nrProtocoloInvalido);

        assertThrows(
                PedidoNaoEncontradoExcecao.class, () -> pagamentoController.atualizaPagamento(nrProtocoloInvalido));

        verify(pagamentoAtualizaUseCase).atualizaPagamento(nrProtocoloInvalido);
        verifyNoInteractions(pagamentoPresenter);
    }
}
