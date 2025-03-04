package br.com.on.fiap.hexagono.casodeuso.produto;

import br.com.on.fiap.hexagono.adaptadores.gateways.ProdutoGateway;
import br.com.on.fiap.hexagono.casodeuso.produto.entrada.DeletaProdutoCasoDeUso;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;

public class DeletaProdutoCasoDeUsoImpl implements DeletaProdutoCasoDeUso {

    private final ProdutoGateway produtoGateway;

    public DeletaProdutoCasoDeUsoImpl(ProdutoGateway persisteProdutoPortaSaida) {
        this.produtoGateway = persisteProdutoPortaSaida;
    }

    @Override
    public void deleta(Long id) {
        produtoGateway.buscaProdutoPorId(id).ifPresentOrElse(produto -> produtoGateway.deletaProdutoPorId(id), () -> {
            throw new ProdutoNaoEncontradoExcecao(MessageError.MSG_ERRO_PRODUTO_NAO_CADASTRADO.getMensagem(), id);
        });
    }
}
