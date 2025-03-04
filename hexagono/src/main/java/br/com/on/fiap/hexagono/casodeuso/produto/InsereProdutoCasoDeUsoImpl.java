package br.com.on.fiap.hexagono.casodeuso.produto;

import br.com.on.fiap.hexagono.casodeuso.produto.entrada.InsereProdutoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.produto.saida.PersisteProdutoPortaSaida;
import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import java.util.Optional;

public class InsereProdutoCasoDeUsoImpl implements InsereProdutoCasoDeUso {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public InsereProdutoCasoDeUsoImpl(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public Produto inserir(Produto produto) {
        Optional<Produto> produtoBancoDados = persisteProdutoPortaSaida.buscaProdutoPorNome(produto.getNome());
        produtoBancoDados.ifPresent(p -> {
            throw new ProdutoExistenteExcecao(
                    MessageError.MSG_ERRO_PRODUTO_JA_CADASTRADO.getMensagem(), produto.getNome());
        });
        return persisteProdutoPortaSaida.salvaProduto(produto);
    }
}
