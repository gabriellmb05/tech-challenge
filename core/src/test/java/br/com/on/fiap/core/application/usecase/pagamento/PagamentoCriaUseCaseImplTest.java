package br.com.on.fiap.core.application.usecase.pagamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.core.application.dto.entrada.PagamentoEntrada;
import br.com.on.fiap.core.application.usecase.pagamento.impl.PagamentoCriaUseCaseImpl;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.core.domain.SituacaoPagamento;
import br.com.on.fiap.core.domain.TipoPagamento;
import br.com.on.fiap.datapool.ProdutoDataPool;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PagamentoCriaUseCaseImplTest {

    @Mock
    private Produto produto;

    @InjectMocks
    private PagamentoCriaUseCaseImpl pagamentoCriaUseCase;

    @Test
    @DisplayName(
            "Dado um pagamento de entrada e produtos, quando criar pagamento, então o pagamento deve ser criado corretamente com o valor total calculado")
    void dadoPagamentoEntradaEPagamentoProdutos_quandoCriarPagamento_entaoPagamentoDeveSerCriadoCorretamente() {
        PagamentoEntrada pagamentoEntrada = PagamentoEntrada.create(1);

        SituacaoPagamento situacaoPagamento = SituacaoPagamento.PENDENTE;
        Pagamento pagamento = pagamentoCriaUseCase.criarPagamento(
                pagamentoEntrada, situacaoPagamento, Map.of(ProdutoDataPool.criarProdutoExistente(1L), 10L));

        assertNotNull(pagamento);
        assertEquals(situacaoPagamento, pagamento.getStPagamento());
        assertEquals(TipoPagamento.CREDITO, pagamento.getTpPagamento());
        assertEquals(BigDecimal.TEN, pagamento.getVlCompra());
    }

    @Test
    @DisplayName(
            "Dado um pagamento de entrada com valor 0 e produtos, quando criar pagamento, então o valor total deve ser 0")
    void dadoPagamentoEntradaComProdutosZero_quandoCriarPagamento_entaoValorTotalDeveSerZero() {
        PagamentoEntrada pagamentoEntrada = PagamentoEntrada.create(2);
        SituacaoPagamento situacaoPagamento = SituacaoPagamento.PENDENTE;
        Pagamento pagamento = pagamentoCriaUseCase.criarPagamento(
                pagamentoEntrada, situacaoPagamento, Map.of(ProdutoDataPool.criarProdutoExistente(1L), 0L));

        assertNotNull(pagamento);
        assertEquals(BigDecimal.ZERO, pagamento.getVlCompra());
    }
}
