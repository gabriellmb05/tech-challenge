package br.com.on.fiap.hexagono.casodeuso.produto;

import br.com.on.fiap.hexagono.adaptadores.gateways.ProdutoGateway;
import br.com.on.fiap.hexagono.casodeuso.produto.entrada.BuscaProdutoPorIdCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import java.util.Optional;

public class BuscaProdutoCasoDeUsoImpl implements BuscaProdutoPorIdCasoDeUso {

    private final ProdutoGateway produtoGateway;

    public BuscaProdutoCasoDeUsoImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto buscar(Long id) {
        Optional<Produto> produtoBancoDados = produtoGateway.buscaProdutoPorId(id);
        return produtoBancoDados.orElseThrow(
                () -> new ProdutoNaoEncontradoExcecao(MessageError.MSG_ERRO_PRODUTO_NAO_CADASTRADO.getMensagem(), id));
    }
}
