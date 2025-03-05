package br.com.on.fiap.core.application.twqt.pagamento;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.on.fiap.core.adapter.gateway.PagamentoGateway;
import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.application.usecase.pagamento.impl.PagamentoAtualizaUseCaseImpl;
import br.com.on.fiap.datapool.DataPoolPagamento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PagamentoAtualizaUseCaseImplTest {

    @Mock
    private PagamentoGateway pagamentoGateway;

    @InjectMocks
    private PagamentoAtualizaUseCaseImpl pagamentoAtualizaUseCase;

    @Test
    @DisplayName(
            "Dado um pagamento para atualização, quando atualizar o pagamento, então o pagamento deve ser integrado e salvo")
    void dadoPagamentoParaAtualizacao_quandoAtualizarPagamento_entaoPagamentoDeveSerIntegradoESalvo() {
        Pagamento pagamento = DataPoolPagamento.pagamentoParaAtualizacao();

        pagamentoAtualizaUseCase.atualizaPagamento(pagamento);

        verify(pagamentoGateway, times(1)).integracaoEnviaPagamento(pagamento);
        verify(pagamentoGateway, times(1)).salvaPagamentoFinalizado(pagamento);
    }

    @Test
    @DisplayName(
            "Dado um pagamento finalizado, quando atualizar o pagamento, então o pagamento deve ser integrado e salvo")
    void dadoPagamentoFinalizado_quandoAtualizarPagamento_entaoPagamentoDeveSerIntegradoESalvo() {
        Pagamento pagamento = DataPoolPagamento.pagamentoFinalizado();

        pagamentoAtualizaUseCase.atualizaPagamento(pagamento);

        verify(pagamentoGateway, times(1)).integracaoEnviaPagamento(pagamento);
        verify(pagamentoGateway, times(1)).salvaPagamentoFinalizado(pagamento);
    }
}
