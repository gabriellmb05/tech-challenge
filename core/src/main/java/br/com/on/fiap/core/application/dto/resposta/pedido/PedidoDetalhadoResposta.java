package br.com.on.fiap.core.application.dto.resposta.pedido;

import br.com.on.fiap.core.application.dto.resposta.cliente.ClienteResposta;
import br.com.on.fiap.core.application.dto.resposta.pagamento.PagamentoResposta;
import br.com.on.fiap.core.application.dto.resposta.produto.ProdutoResposta;
import br.com.on.fiap.core.domain.SituacaoPedido;
import java.util.List;

public interface PedidoDetalhadoResposta {

    Long getId();

    ClienteResposta getCliente();

    SituacaoPedido getSituacao();

    List<ProdutoResposta> getProdutos();

    PagamentoResposta getPagamento();

    static PedidoDetalhadoResposta create(
            Long id,
            ClienteResposta cliente,
            SituacaoPedido situacao,
            List<ProdutoResposta> produtos,
            PagamentoResposta pagamento) {
        return new PedidoDetalhadoResposta() {
            @Override
            public Long getId() {
                return id;
            }

            @Override
            public ClienteResposta getCliente() {
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
