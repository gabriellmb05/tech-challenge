package br.com.on.fiap.hexagono.casodeuso.produto;

import br.com.on.fiap.hexagono.casodeuso.produto.entrada.AlteraProdutoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.produto.saida.PersisteProdutoPortaSaida;
import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;

public class AlteraProdutoCasoDeUsoImpl implements AlteraProdutoCasoDeUso {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public AlteraProdutoCasoDeUsoImpl(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public Produto alterar(Long id, Produto produto) {
        Produto produtoEncontrado = persisteProdutoPortaSaida
                .buscaProdutoPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_PRODUTO_NAO_CADASTRADO.getMensagem(), id));
        produtoEncontrado.atualizarDados(produto);
        return persisteProdutoPortaSaida.salvaProduto(produtoEncontrado);
    }
}
