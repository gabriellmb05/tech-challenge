package br.com.on.fiap.hexagono.usecases.casodeuso.produto;

import br.com.on.fiap.hexagono.entities.entidades.Produto;
import br.com.on.fiap.hexagono.usecases.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.usecases.excecao.message.MessageError;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.produto.AlteraProdutoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.produto.PersisteProdutoPortaSaida;

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
