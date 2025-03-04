package br.com.on.fiap.hexagono.casodeuso.pagamento;

import static org.junit.jupiter.api.Assertions.*;

import br.com.on.fiap.hexagono.datapool.DataPoolPagamento;
import br.com.on.fiap.hexagono.entidades.Pagamento;
import br.com.on.fiap.hexagono.entidades.SituacaoPagamento;
import br.com.on.fiap.hexagono.excecao.PagamentoJaRealizadoExcecao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidaPagamentoCasoDeUsoImplTest {

    @InjectMocks
    private ValidaPagamentoCasoDeUsoImpl validaPagamentoCasoDeUsoImpl;

    @Test
    @DisplayName(
            "Dado um pagamento aprovado com data, quando validar o pagamento, então deve lançar a exceção PagamentoJaRealizadoExcecao")
    void dadoPagamentoAprovadoComData_quandoValidarPagamento_EntaoDeveLancarExcecao() {
        Pagamento pagamento = DataPoolPagamento.pagamentoValido();
        String nrProtocolo = "123ABC";

        assertThrows(
                PagamentoJaRealizadoExcecao.class,
                () -> validaPagamentoCasoDeUsoImpl.validarPagamentoJaRealizado(pagamento, nrProtocolo));
    }

    @Test
    @DisplayName("Dado um pagamento aprovado sem data, quando validar o pagamento, então não deve lançar exceção")
    void dadoPagamentoAprovadoSemData_quandoValidarPagamento_EntaoNaoDeveLancarExcecao() {
        Pagamento pagamento = DataPoolPagamento.pagamentoValidoSemData();
        String nrProtocolo = "123ABC";

        assertDoesNotThrow(() -> validaPagamentoCasoDeUsoImpl.validarPagamentoJaRealizado(pagamento, nrProtocolo));
    }

    @Test
    @DisplayName("Dado um pagamento pendente, quando validar o pagamento, então não deve lançar exceção")
    void dadoPagamentoPendente_quandoValidarPagamento_EntaoNaoDeveLancarExcecao() {
        Pagamento pagamento = DataPoolPagamento.pagamentoPendente();
        String nrProtocolo = "123ABC";

        validaPagamentoCasoDeUsoImpl.validarPagamentoJaRealizado(pagamento, nrProtocolo);

        assertDoesNotThrow(() -> validaPagamentoCasoDeUsoImpl.validarPagamentoJaRealizado(pagamento, nrProtocolo));
        assertEquals(SituacaoPagamento.PENDENTE, pagamento.getStPagamento());
    }
}
