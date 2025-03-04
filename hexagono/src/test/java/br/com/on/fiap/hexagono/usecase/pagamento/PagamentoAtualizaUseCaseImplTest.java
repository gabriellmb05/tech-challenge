package br.com.on.fiap.hexagono.usecase.pagamento;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.on.fiap.hexagono.datapool.DataPoolPagamento;
import br.com.on.fiap.hexagono.domain.entity.Pagamento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PagamentoAtualizaUseCaseImplTest {

    @Mock
    private IntegracaoPagamentoSaida integracaoPagamentoSaida;

    @Mock
    private PersistePagamentoPortaSaida persistePagamentoPortaSaida;

    @InjectMocks
    private PagamentoAtualizaUseCaseImpl atualizaPagamentoCasoDeUsoImpl;

    @Test
    @DisplayName(
            "Dado um pagamento para atualização, quando atualizar o pagamento, então o pagamento deve ser integrado e salvo")
    void dadoPagamentoParaAtualizacao_quandoAtualizarPagamento_entaoPagamentoDeveSerIntegradoESalvo() {
        Pagamento pagamento = DataPoolPagamento.pagamentoParaAtualizacao();

        atualizaPagamentoCasoDeUsoImpl.atualizaPagamento(pagamento);

        verify(integracaoPagamentoSaida, times(1)).integracaoEnviaPagamento(pagamento);
        verify(persistePagamentoPortaSaida, times(1)).salvaPagamentoFinalizado(pagamento);
    }

    @Test
    @DisplayName(
            "Dado um pagamento finalizado, quando atualizar o pagamento, então o pagamento deve ser integrado e salvo")
    void dadoPagamentoFinalizado_quandoAtualizarPagamento_entaoPagamentoDeveSerIntegradoESalvo() {
        Pagamento pagamento = DataPoolPagamento.pagamentoFinalizado();

        atualizaPagamentoCasoDeUsoImpl.atualizaPagamento(pagamento);

        verify(integracaoPagamentoSaida, times(1)).integracaoEnviaPagamento(pagamento);
        verify(persistePagamentoPortaSaida, times(1)).salvaPagamentoFinalizado(pagamento);
    }
}
