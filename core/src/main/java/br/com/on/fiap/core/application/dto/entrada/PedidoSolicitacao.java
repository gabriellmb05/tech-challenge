package br.com.on.fiap.core.application.dto.entrada;

import java.util.List;

public interface PedidoSolicitacao {

    Long getCliente();

    List<ProdutoQuantidadeSolicitacao> getProdutos();

    PagamentoSolicitacao getPagamento();

    static PedidoSolicitacao create(
            Long cliente, List<ProdutoQuantidadeSolicitacao> produtos, PagamentoSolicitacao pagamento) {
        return new PedidoSolicitacao() {
            @Override
            public Long getCliente() {
                return cliente;
            }

            @Override
            public List<ProdutoQuantidadeSolicitacao> getProdutos() {
                return produtos;
            }

            @Override
            public PagamentoSolicitacao getPagamento() {
                return pagamento;
            }
        };
    }
}
