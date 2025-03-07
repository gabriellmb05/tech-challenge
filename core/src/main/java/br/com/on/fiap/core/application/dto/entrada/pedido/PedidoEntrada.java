package br.com.on.fiap.core.application.dto.entrada.pedido;

import br.com.on.fiap.core.application.dto.entrada.pagamento.PagamentoEntrada;
import br.com.on.fiap.core.application.dto.entrada.produto.ProdutoQuantidadeEntrada;
import java.util.List;

public interface PedidoEntrada {

    Long getCliente();

    List<ProdutoQuantidadeEntrada> getProdutos();

    PagamentoEntrada getPagamento();

    static PedidoEntrada create(Long cliente, List<ProdutoQuantidadeEntrada> produtos, PagamentoEntrada pagamento) {
        return new PedidoEntrada() {
            @Override
            public Long getCliente() {
                return cliente;
            }

            @Override
            public List<ProdutoQuantidadeEntrada> getProdutos() {
                return produtos;
            }

            @Override
            public PagamentoEntrada getPagamento() {
                return pagamento;
            }
        };
    }
}
