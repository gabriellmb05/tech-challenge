package br.com.on.fiap.hexagono.casosdeuso.pagamento;

import br.com.on.fiap.hexagono.datapool.DataPoolPagamento;
import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.portas.saida.integracao.IntegracaoPagamentoSaida;
import br.com.on.fiap.hexagono.portas.saida.pagamento.PersistePagamentoPortaSaida;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AtualizaPagamentoCasoDeUsoTest {

    @Mock
    private IntegracaoPagamentoSaida integracaoPagamentoSaida;

    @Mock
    private PersistePagamentoPortaSaida persistePagamentoPortaSaida;

    @InjectMocks
    private AtualizaPagamentoCasoDeUso atualizaPagamentoCasoDeUso;

    @Test
    @DisplayName("Dado um pagamento para atualização, quando atualizar o pagamento, então o pagamento deve ser integrado e salvo")
    void dadoPagamentoParaAtualizacao_quandoAtualizarPagamento_entaoPagamentoDeveSerIntegradoESalvo() {
        Pagamento pagamento = DataPoolPagamento.pagamentoParaAtualizacao();

        atualizaPagamentoCasoDeUso.atualizaPagamento(pagamento);

        verify(integracaoPagamentoSaida, times(1)).integracaoEnviaPagamento(pagamento);
        verify(persistePagamentoPortaSaida, times(1)).salvaPagamentoFinalizado(pagamento);
    }

    @Test
    @DisplayName("Dado um pagamento finalizado, quando atualizar o pagamento, então o pagamento deve ser integrado e salvo")
    void dadoPagamentoFinalizado_quandoAtualizarPagamento_entaoPagamentoDeveSerIntegradoESalvo() {
        Pagamento pagamento = DataPoolPagamento.pagamentoFinalizado();

        atualizaPagamentoCasoDeUso.atualizaPagamento(pagamento);

        verify(integracaoPagamentoSaida, times(1)).integracaoEnviaPagamento(pagamento);
        verify(persistePagamentoPortaSaida, times(1)).salvaPagamentoFinalizado(pagamento);
    }
}
