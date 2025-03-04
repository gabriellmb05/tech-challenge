package br.com.on.fiap.hexagono.casodeuso.produto;

import br.com.on.fiap.hexagono.adaptadores.gateways.ProdutoGateway;
import br.com.on.fiap.hexagono.casodeuso.produto.entrada.InsereProdutoCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import java.util.Optional;

public class InsereProdutoCasoDeUsoImpl implements InsereProdutoCasoDeUso {

    private final ProdutoGateway produtoGateway;

    public InsereProdutoCasoDeUsoImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto inserir(Produto produto) {
        Optional<Produto> produtoBancoDados = produtoGateway.buscaProdutoPorNome(produto.getNome());
        produtoBancoDados.ifPresent(p -> {
            throw new ProdutoExistenteExcecao(
                    MessageError.MSG_ERRO_PRODUTO_JA_CADASTRADO.getMensagem(), produto.getNome());
        });
        return produtoGateway.salvaProduto(produto);
    }
}
