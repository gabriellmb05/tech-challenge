package br.com.on.fiap.core.domain.model;

import java.util.List;

public interface PedidoDetalhadoResposta {

    Long getId();

    ClienteRespostaDTO getCliente();

    SituacaoPedido getSituacao();

    List<ProdutoResposta> getProdutos();

    PagamentoResposta getPagamento();

    static PedidoDetalhadoResposta create(
            Long id,
            ClienteRespostaDTO cliente,
            SituacaoPedido situacao,
            List<ProdutoResposta> produtos,
            PagamentoResposta pagamento) {
        return new PedidoDetalhadoResposta() {
            @Override
            public Long getId() {
                return id;
            }

            @Override
            public ClienteRespostaDTO getCliente() {
                return cliente;
            }

            @Override
            public SituacaoPedido getSituacao() {
                return situacao;
            }

            @Override
            public List<ProdutoResposta> getProdutos() {
                return produtos;
            }

            @Override
            public PagamentoResposta getPagamento() {
                return pagamento;
            }
        };
    }
}
