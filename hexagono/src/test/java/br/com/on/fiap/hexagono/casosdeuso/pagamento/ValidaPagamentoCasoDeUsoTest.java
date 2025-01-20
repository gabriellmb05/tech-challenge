package br.com.on.fiap.hexagono.casosdeuso.pagamento;

import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.on.fiap.hexagono.datapool.DataPoolPagamento;
import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.excecao.PagamentoJaRealizadoExcecao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidaPagamentoCasoDeUsoTest {

    @InjectMocks
    private ValidaPagamentoCasoDeUso validaPagamentoCasoDeUso;

    @Test
    @DisplayName(
            "Dado um pagamento aprovado com data, quando validar o pagamento, então deve lançar a exceção PagamentoJaRealizadoExcecao")
    void dadoPagamentoAprovadoComData_quandoValidarPagamento_EntaoDeveLancarExcecao() {
        Pagamento pagamento = DataPoolPagamento.pagamentoValido();
        String nrProtocolo = "123ABC";

        assertThrows(
                PagamentoJaRealizadoExcecao.class,
                () -> validaPagamentoCasoDeUso.validarPagamentoJaRealizado(pagamento, nrProtocolo));
    }

    @Test
    @DisplayName("Dado um pagamento pendente, quando validar o pagamento, então não deve lançar exceção")
    void dadoPagamentoPendente_quandoValidarPagamento_EntaoNaoDeveLancarExcecao() {
        Pagamento pagamento = DataPoolPagamento.pagamentoPendente();
        String nrProtocolo = "123ABC";

        validaPagamentoCasoDeUso.validarPagamentoJaRealizado(pagamento, nrProtocolo);
    }
}
