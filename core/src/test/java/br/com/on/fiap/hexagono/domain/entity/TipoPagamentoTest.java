package br.com.on.fiap.hexagono.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import br.com.on.fiap.hexagono.domain.exception.TipoPagamentoNaoEncontradoExcecao;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TipoPagamentoTest {

    @Test
    @DisplayName(
            "Dado um valor de código quando buscar um tipo de pagamento, então o tipo de pagamento deve ser retornado")
    void dadoCodigoValido_quandoBuscarTipoPagamento_entaoTipoPagamentoDeveSerRetornado() {
        List<Integer> codigos = Arrays.stream(TipoPagamento.values())
                .map(TipoPagamento::getCodigo)
                .toList();
        for (Integer codigo : codigos) {
            TipoPagamento tipoPagamento = TipoPagamento.deCodigo(codigo);
            assertNotNull(tipoPagamento);
            assertEquals(codigo, tipoPagamento.getCodigo());
        }
    }

    @Test
    @DisplayName(
            "Dado um valor de código inválido quando buscar uma tipo de pagamento, então um erro deve ser retornado")
    void dadoCodigoInvalido_quandoBuscarTipoPagamento_entaoErroDeveSerRetornado() {
        int codigoInvalido = 4;
        TipoPagamentoNaoEncontradoExcecao exception =
                assertThrows(TipoPagamentoNaoEncontradoExcecao.class, () -> TipoPagamento.deCodigo(codigoInvalido));
        assertEquals("Tipo de pagamento (4) não encontrado", exception.getMessage());
    }
}
