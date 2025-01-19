package br.com.on.fiap.hexagono.casosdeuso.produto;

import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.produto.AlteraProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;

public class AlteraProdutoCasoDeUso implements AlteraProdutoPortaEntrada {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public AlteraProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
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
