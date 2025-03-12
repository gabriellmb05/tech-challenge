package br.com.on.fiap.core.application.usecase.pagamento;

import static org.junit.jupiter.api.Assertions.*;

import br.com.on.fiap.core.application.exception.PagamentoJaRealizadoExcecao;
import br.com.on.fiap.core.application.usecase.pagamento.impl.PagamentoValidaUseCaseImpl;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.SituacaoPagamento;
import br.com.on.fiap.datapool.PagamentoDataPool;
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
        assertThrows(
                PagamentoJaRealizadoExcecao.class,
                () -> pagamentoValidaUseCase.validarPagamentoJaRealizado(
                        PagamentoDataPool.criarPagamentoValido(), "123ABC"));
    }

    @Test
    @DisplayName("Dado um pagamento aprovado sem data, quando validar o pagamento, então não deve lançar exceção")
    void dadoPagamentoAprovadoSemData_quandoValidarPagamento_EntaoNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> pagamentoValidaUseCase.validarPagamentoJaRealizado(
                PagamentoDataPool.criarPagamentoValidoSemData(), "123ABC"));
    }

    @Test
    @DisplayName("Dado um pagamento pendente, quando validar o pagamento, então não deve lançar exceção")
    void dadoPagamentoPendente_quandoValidarPagamento_EntaoNaoDeveLancarExcecao() {
        Pagamento pagamento = PagamentoDataPool.criarPagamentoPendente();
        String nrProtocolo = "123ABC";

        pagamentoValidaUseCase.validarPagamentoJaRealizado(pagamento, nrProtocolo);

        assertDoesNotThrow(() -> pagamentoValidaUseCase.validarPagamentoJaRealizado(pagamento, nrProtocolo));
        assertEquals(SituacaoPagamento.PENDENTE, pagamento.getStPagamento());
    }
}
