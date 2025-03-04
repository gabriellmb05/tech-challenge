package br.com.on.fiap.hexagono.casodeuso.produto;

import br.com.on.fiap.hexagono.adaptadores.gateways.ProdutoGateway;
import br.com.on.fiap.hexagono.casodeuso.produto.entrada.AlteraProdutoCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;

public class AlteraProdutoCasoDeUsoImpl implements AlteraProdutoCasoDeUso {

    private final ProdutoGateway produtoGateway;

    public AlteraProdutoCasoDeUsoImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto alterar(Long id, Produto produto) {
        Produto produtoEncontrado = produtoGateway
                .buscaProdutoPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_PRODUTO_NAO_CADASTRADO.getMensagem(), id));
        produtoEncontrado.atualizarDados(produto);
        return produtoGateway.salvaProduto(produtoEncontrado);
    }
}
