package br.com.on.fiap.core.application.usecase.pagamento.impl;

import br.com.on.fiap.core.application.dto.entrada.PagamentoEntrada;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoCriaUseCase;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.core.domain.SituacaoPagamento;
import br.com.on.fiap.core.domain.TipoPagamento;
import java.math.BigDecimal;
import java.util.Map;

public class PagamentoCriaUseCaseImpl implements PagamentoCriaUseCase {

    @Override
    public Pagamento criarPagamento(
            PagamentoEntrada pagamentoEntrada,
            SituacaoPagamento situacaoPagamento,
            Map<Produto, Long> valoresProdutos) {
        Pagamento pagamento = new Pagamento();
        pagamento.setStPagamento(situacaoPagamento);
        pagamento.setTpPagamento(TipoPagamento.deCodigo(pagamentoEntrada.getTpPagamento()));
        pagamento.setVlCompra(calcularValorTotalPedido(valoresProdutos));
        return pagamento;
    }

    private BigDecimal calcularValorTotalPedido(Map<Produto, Long> valoresProdutos) {
        return valoresProdutos.entrySet().stream()
                .map(produtoLongEntry ->
                        produtoLongEntry.getKey().getPreco().multiply(BigDecimal.valueOf(produtoLongEntry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
