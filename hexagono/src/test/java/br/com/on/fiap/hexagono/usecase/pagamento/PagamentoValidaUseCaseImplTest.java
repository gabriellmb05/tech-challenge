package br.com.on.fiap.hexagono.usecase.pagamento;

import static org.junit.jupiter.api.Assertions.*;

import br.com.on.fiap.hexagono.datapool.DataPoolPagamento;
import br.com.on.fiap.hexagono.domain.entity.Pagamento;
import br.com.on.fiap.hexagono.domain.entity.SituacaoPagamento;
import br.com.on.fiap.hexagono.domain.exception.PagamentoJaRealizadoExcecao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PagamentoValidaUseCaseImplTest {

    @InjectMocks
    private PagamentoValidaUseCaseImpl pagamentoValidaUseCase;

    @Test
    @DisplayName(
            "Dado um pagamento aprovado com data, quando validar o pagamento, então deve lançar a exceção PagamentoJaRealizadoExcecao")
    void dadoPagamentoAprovadoComData_quandoValidarPagamento_EntaoDeveLancarExcecao() {
        Pagamento pagamento = DataPoolPagamento.pagamentoValido();
        String nrProtocolo = "123ABC";

        assertThrows(
                PagamentoJaRealizadoExcecao.class,
                () -> pagamentoValidaUseCase.validarPagamentoJaRealizado(pagamento, nrProtocolo));
    }

    @Test
    @DisplayName("Dado um pagamento aprovado sem data, quando validar o pagamento, então não deve lançar exceção")
    void dadoPagamentoAprovadoSemData_quandoValidarPagamento_EntaoNaoDeveLancarExcecao() {
        Pagamento pagamento = DataPoolPagamento.pagamentoValidoSemData();
        String nrProtocolo = "123ABC";

        assertDoesNotThrow(() -> pagamentoValidaUseCase.validarPagamentoJaRealizado(pagamento, nrProtocolo));
    }

    @Test
    @DisplayName("Dado um pagamento pendente, quando validar o pagamento, então não deve lançar exceção")
    void dadoPagamentoPendente_quandoValidarPagamento_EntaoNaoDeveLancarExcecao() {
        Pagamento pagamento = DataPoolPagamento.pagamentoPendente();
        String nrProtocolo = "123ABC";

        pagamentoValidaUseCase.validarPagamentoJaRealizado(pagamento, nrProtocolo);

        assertDoesNotThrow(() -> pagamentoValidaUseCase.validarPagamentoJaRealizado(pagamento, nrProtocolo));
        assertEquals(SituacaoPagamento.PENDENTE, pagamento.getStPagamento());
    }
}
